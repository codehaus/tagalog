/*
 * $Id: AbstractTagLibrary.java,v 1.8 2005-04-26 14:26:38 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Iterator;
import java.util.Map;

/**
 * Standard implementation of the <code>TagLibrary</code> contract.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.8 $
 */
public abstract class AbstractTagLibrary implements TagLibrary {
    private Map handlerPool = new java.util.TreeMap();

    protected void registerTagBinding(TagBinding tagBinding) {
        handlerPool.put(tagBinding.getName(),
                        new NodeHandlerPool(tagBinding));
    }

    public NodeHandler getNodeHandler(String element) {
        NodeHandlerPool info;

        if (element.length() == 0)
            throw new IllegalArgumentException("handler name is empty");
        info = (NodeHandlerPool) handlerPool.get(element);
        if (info == null)
            return null;
        return info.getNodeHandler();
    }

    public void releaseNodeHandler(String element, NodeHandler tag) {
        NodeHandlerPool info = (NodeHandlerPool) handlerPool.get(element);
        if (info == null)
            throw new IllegalStateException("could not find tag " + tag
                                          + " for element '" + element + "'");
        info.releaseNodeHandler(tag);
    }

    public String listUnreleasedTags() {
        StringBuffer buf = new StringBuffer();
        Iterator iter = handlerPool.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            NodeHandlerPool info = (NodeHandlerPool) entry.getValue();
            if (info.unreleasedTagCount() > 0) {
                if (buf.length() > 0)
                    buf.append(", ");
                buf.append((String) entry.getKey());
                buf.append(": ");
                buf.append(info.unreleasedTagCount());
            }
        }
        return buf.toString();
    }

    private static final class NodeHandlerPool {
        private static final int INITIAL_SIZE = 20;

        private final TagBinding tagBinding;

        private NodeHandler[] tagInstances;

        /** End of the used portion of the <code>tagInstances</code> list. */
        private int used = 0;

        /** End of the unused portion of the <code>tagInstances</code> list. */
        private int unused = 0;

        NodeHandlerPool(TagBinding tagBinding) {
            this.tagBinding = tagBinding;

            // Check that we can create instances of the tag.
            NodeHandler tag = instantiateNodeHandler(true);
            tagInstances = new NodeHandler[INITIAL_SIZE];
            tagInstances[unused++] = tag;
        }

        private void extend() {
            NodeHandler[] newArray = new NodeHandler[tagInstances.length + INITIAL_SIZE];
            System.arraycopy(tagInstances, 0, newArray, 0, tagInstances.length);
            tagInstances = newArray;
        }

        synchronized NodeHandler getNodeHandler() {
            NodeHandler tag;

            if (used == unused) {
                tag = instantiateNodeHandler(false);
                if (unused == tagInstances.length)
                    extend();
                tagInstances[unused++] = tag;
                used++;
            } else {
                tag = tagInstances[used++];
            }
            return tag;
        }

        synchronized void releaseNodeHandler(NodeHandler tag) {
            for (int i = used-1; i >= 0; i--) {
                if (tag == tagInstances[i]) {
                    if (!tag.recycle()) {
                        tagInstances[i] = tagInstances[--used];
                        if (--unused != used) {
                            tagInstances[used] = tagInstances[unused];
                            tagInstances[unused] = null;
                        }
                    } else {
                        if (i < --used) {
                            NodeHandler tmp = tagInstances[i];
                            tagInstances[i] = tagInstances[used];
                            tagInstances[used] = tmp;
                        }
                    }
                    return;
                }
            }
            throw new IllegalArgumentException("could not find tag " + tag);
        }

        private NodeHandler instantiateNodeHandler(boolean firstTime) {
            NodeHandler nodeHandler;
            Class handlerClass = tagBinding.getNodeHandlerClass();

            try {
                nodeHandler = (NodeHandler) handlerClass.newInstance();
            } catch (Exception e) {
                if (firstTime) {
                    String className = handlerClass.getName();
                    throw new IllegalArgumentException("could not instantiate "
                                                       + className
                                                       + ": " + e);
                }
                throw new Error("exception instantiating tag", e);
            }
            nodeHandler.setTagBinding(tagBinding);
            return nodeHandler;
        }

        int unreleasedTagCount() {
            return used;
        }
    }
}
