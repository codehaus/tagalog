/*
 * $Id: AbstractTag.java,v 1.10 2004-11-15 14:21:34 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * AbstractTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.10 $
 */
public abstract class AbstractTag implements Tag {
    private TagalogParser parser;

    private Map context;

    private Tag parent;

    public void setParser(TagalogParser parser) {
        this.parser = parser;
    }

    public TagalogParser getParser() {
        return parser;
    }

    public void setContext(Map context) {
        this.context = context;
    }

    protected Map getContext() {
        return context;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public Tag getParent() {
        return parent;
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
    }

    public void child(Object child)
        throws TagException, TagalogParseException
    {
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        return null;
    }

    public boolean recycle() {
        return true;
    }

    //
    // Convenience methods to make tag implementation easier.
    //

    protected final Location getLocation() {
        return parser.getLocation();
    }

    protected final String requireAttribute(Attributes attributes,
                                            String elementName,
                                            String attributeName)
        throws TagException
    {
        return TagUtils.requireAttribute(attributes, elementName,
                                         attributeName);
    }

    /**
     * Assert that this tag's parent is of a given class, allowing nesting
     * relationships to be enforced. This would typically be used in the
     * {@link #begin(String, Attributes)} method:
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
     * @param childTag The tag to find the ancestor from. Must not be
     * <code>null</code>.
     * @param ancestorTagClass The class of the ancestor to find. Must not be
     * <code>null</code>.
     * @return The first ancestor of the supplied tag that matches the
     * supplied class.  If no match is found, then <code>null</code> is
     * returned.
     * @throws NullPointerException If either argument is <code>null</code>.
     */
    protected final Tag findAncestor(Class ancestorTagClass) {
        return TagUtils.findAncestor(this, ancestorTagClass);
    }

    /**
     * Returns the first ancestor of the supplied tag that matches the
     * supplied class, raising an exception if no match is found.
     *
     * @param childTag The tag to find the ancestor from.
     * @param tagName The tag name of the ancestor to find.
     * @param ancestorTagClass The class of the ancestor to find.
     * @return the first ancestor of the supplied tag that matches the
     * supplied class.
     * @throws TagException If no ancestor is found.
     * @throws NullPointerException If any supplied arguments are null.
     */
    protected final Tag requireAncestor(String tagName, Class ancestorTagClass)
        throws TagException
    {
        return TagUtils.requireAncestor(this, tagName, ancestorTagClass);
    }
}
