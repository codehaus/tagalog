/*
 * $Id: NodeHandler.java,v 1.3 2005-05-17 21:15:47 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * The <code>NodeHandler</code> interface defines the basic lifecycle of the
 * objects that handle XML elements and processing instructions. The
 * {@link TagalogParser} gets instances of <code>NodeHandler</code> from
 * the appropriate {@link TagLibrary} and calls the
 * <code>NodeHandler</code>'s methods in accordance with this specification.
 * The <code>Tag</code> lifecycle consists of three phases: initialisation,
 * linking and unlinking.
 *
 * <dl>
 * <dt><a name="init">Initialisation</a></dt>
 * <dd>
 * An instance of the node handler is constructed using its default
 * constructor. The {@link #setTagBinding} method is called once to
 * inform the node handler of the name/class binding that it will handle.
 * The tag binding can be consulted to decide which XML element or
 * processing instruction the node handler has been created to handle
 * (this is useful because some node handlers may be required to deal
 * with many different elements in a document).
 * The node handler's binding will not subsequently change.
 * </dd>
 * <dt>Linking</dt>
 * <dd>
 * Before a node handler is used to handle a specific XML element or
 * processing instruction it is linked into the current parse state.
 * This involves calling the {@link #setParser}, {@link #setContext}
 * and {@link #setParent} methods in that order.
 * If the node handler is processing the root element of the document,
 * or a processing instruction that is outside the root element, then
 * <code>setParent</code> will not be called.
 * </dd>
 * <dt>Unlinking</dt>
 * <dd>
 * After handling an XML element or processing instruction the node handler
 * if unlinked from the parse state and prepared for re-use.
 * The {@link #setParent}, {@link #setContext} and {@link #setParser}
 * methods are called with the parameter <code>null</code> to disconnect
 * the node handler from the its parent, the shared parse context and
 * the parser.
 * If the node handler is processing the root element of the document,
 * or a processing instruction that is outside the root element, then
 * <code>setParent</code> will not be called.
 * Finally the {@link #recycle} method is called to enquire whether the
 * node handler can be reused. Implementations of this method can either
 * discard any internal state (typically by setting references to
 * <code>null</code>) and return <code>true</code>, or they can return
 * <code>false</code>, in which case the tag instance is discarded by
 * the parser.
 * </dd>
 * </ul>
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public interface NodeHandler {
    /**
     * Called once just after tag construction to inform the node handler
     * of its tag binding.
     *
     * @param tagBinding Immutable object representing the element name
     *                   binding for this node handler.
     */
    void setTagBinding(TagBinding tagBinding);

    /**
     * @return The element name binding for this node handler instance.
     */
    TagBinding getTagBinding();

    void setParser(TagalogParser parser);

    void setContext(Map context);

    void setParent(NodeHandler parent);

    TagalogParser getParser();

    NodeHandler getParent();

    String getName();

    boolean recycle();
}
