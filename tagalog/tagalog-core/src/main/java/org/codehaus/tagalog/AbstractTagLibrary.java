/*
 * $Id: AbstractTagLibrary.java,v 1.6 2005-04-07 15:49:12 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Iterator;
import java.util.Map;

/**
 * Simple implementation of the <code>TagLibrary</code> contract.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.6 $
 */
public abstract class AbstractTagLibrary implements TagLibrary {
    private Map tags = new java.util.TreeMap();

    protected void registerTagBinding(TagBinding tagBinding) {
        tags.put(tagBinding.getElementName(), new TagInfo(tagBinding));
    }

    public Tag getTag(String element) {
        TagInfo info;

        if (element.length() == 0)
            throw new IllegalArgumentException("tag name is empty");
        info = (TagInfo) tags.get(element);
        if (info == null)
            return null;
        return info.getTag();
    }

    public void releaseTag(String element, Tag tag) {
        TagInfo info = (TagInfo) tags.get(element);
        if (info == null)
            throw new IllegalStateException("could not find tag " + tag
                                          + " for element '" + element + "'");
        info.releaseTag(tag);
    }

    public String listUnreleasedTags() {
        StringBuffer buf = new StringBuffer();
        Iterator iter = tags.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            TagInfo info = (TagInfo) entry.getValue();
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

    private static final class TagInfo {
        private static final int INITIAL_SIZE = 20;

        private final TagBinding tagBinding;

        private Tag[] tagInstances;

        /** End of the used portion of the <code>tagInstances</code> list. */
        private int used = 0;

        /** End of the unused portion of the <code>tagInstances</code> list. */
        private int unused = 0;

        TagInfo(TagBinding tagBinding) {
            this.tagBinding = tagBinding;

            // Check that we can create instances of the tag.
            Tag tag = instantiateTag(true);
            tagInstances = new Tag[INITIAL_SIZE];
            tagInstances[unused++] = tag;
        }

        private void extend() {
            Tag[] newArray = new Tag[tagInstances.length + INITIAL_SIZE];
            System.arraycopy(tagInstances, 0, newArray, 0, tagInstances.length);
            tagInstances = newArray;
        }

        synchronized Tag getTag() {
            Tag tag;

            if (used == unused) {
                tag = instantiateTag(false);
                if (unused == tagInstances.length)
                    extend();
                tagInstances[unused++] = tag;
                used++;
            } else {
                tag = tagInstances[used++];
            }
            return tag;
        }

        synchronized void releaseTag(Tag tag) {
            int found;

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
                            Tag tmp = tagInstances[i];
                            tagInstances[i] = tagInstances[used];
                            tagInstances[used] = tmp;
                        }
                    }
                    return;
                }
            }
            throw new IllegalArgumentException("could not find tag " + tag);
        }

        private Tag instantiateTag(boolean firstTime) {
            Tag tag;

            try {
                tag = (Tag) tagBinding.getTagClass().newInstance();
            } catch (Exception e) {
                if (firstTime) {
                    String className = tagBinding.getTagClass().getName();
                    throw new IllegalArgumentException("could not instantiate "
                                                       + className
                                                       + ": " + e);
                } else {
                    throw new Error("exception instantiating tag", e);
                }
            }
            tag.setTagBinding(tagBinding);
            return tag;
        }

        int unreleasedTagCount() {
            return used;
        }
    }
}
