/*
 * $Id: AbstractNodeHandler.java,v 1.4 2005-05-18 11:44:50 krisb Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * Basic implementation of the {@link NodeHandler} interface.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public abstract class AbstractNodeHandler implements NodeHandler {
    private TagBinding tagBinding;

    private TagalogParser parser;

    private Map context;

    private NodeHandler parent;

    public final void setTagBinding(TagBinding tagBinding) {
        this.tagBinding = tagBinding;
    }

    public final TagBinding getTagBinding() {
        return tagBinding;
    }

    public final void setParser(TagalogParser parser) {
        this.parser = parser;
    }

    public final TagalogParser getParser() {
        return parser;
    }

    public final void setContext(Map context) {
        this.context = context;
    }

    protected final Map getContext() {
        return context;
    }

    public final void setParent(NodeHandler parent) {
        this.parent = parent;
    }

    public final NodeHandler getParent() {
        return parent;
    }

    public final String getName() {
        return tagBinding.getName();
    }

    /**
     * Prepare a tag for reuse, if possible.
     *
     * This method can be overridden in one of two ways. If a
     * <code>Tag</code> implementation cannot be reused easily then it
     * should provide an implementation of this method that simply returns
     * <code>false</code>.
     * <p>
     * If an implementation can be reused then its <code>recycle</code>
     * method should set all references to transient objects to
     * <code>null</code> and then return the value of
     * <code>super.recycle()</code>.
     *
     * @return <code>true</code> if the tag instance is prepared to be
     * recycled.
     */
    public boolean recycle() {
        return true;
    }

    //
    // Convenience methods to make node handler implementation easier.
    //

    protected final Location getLocation() {
        return parser.getLocation();
    }

    /**
     * Assert that this tag's parent is of a given class, allowing nesting
     * relationships to be enforced. This would typically be used in the
     * {@link Tag#begin(String, Attributes)} method:
     *
     * <pre>
     * public void begin(String elementName, Attributes attributes)
     *     throws TagException
     * {
     *     requireParent(elementName, "if", IfTag.class);
     *     ...
     * </pre>
     *
     * If the parent tag is not of the specified class the method throws
     * <code>TagException</code> using the <code>myName</code> and
     * <code>parentName</code> parameters to construct a meaningful error
     * message.
     *
     * @param myName String name of this element.
     * @param parentName String name of the parent element
     * @param parentClass Class that the parent tag must match.
     * @throws TagException If the parent tag is not of the required type.
     */
    protected final void requireParent(String myName, String parentName,
                                       Class parentClass)
        throws TagException
    {
        TagUtils.requireParent(this, myName, parentName, parentClass);
    }

    /**
     * Returns the first ancestor of the supplied tag that matches the
     * supplied class.  If no match is found, then <code>null</code> is
     * returned. This is the analogue of the <code>findAncestorWithClass</code>
     * method from Jelly's <code>TagSupport</code> class.
     *
     * @param ancestorTagClass The class of the ancestor to find. Must not be
     * <code>null</code>.
     * @return The first ancestor of the supplied tag that matches the
     * supplied class.  If no match is found, then <code>null</code> is
     * returned.
     * @throws NullPointerException If either argument is <code>null</code>.
     */
    protected final NodeHandler findAncestor(Class ancestorTagClass) {
        return TagUtils.findAncestor(this, ancestorTagClass);
    }

    /**
     * Returns the first ancestor of the supplied tag that matches the
     * supplied class, raising an exception if no match is found.
     *
     * @param tagName The tag name of the ancestor to find.
     * @param ancestorTagClass The class of the ancestor to find.
     * @return the first ancestor of the supplied tag that matches the
     * supplied class.
     * @throws TagException If no ancestor is found.
     * @throws NullPointerException If any supplied arguments are null.
     */
    protected final NodeHandler requireAncestor(String tagName,
                                                Class ancestorTagClass)
        throws TagException
    {
        return TagUtils.requireAncestor(this, tagName, ancestorTagClass);
    }
}
