/*
 * $Id: AbstractTag.java,v 1.8 2004-11-04 17:34:40 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * AbstractTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.8 $
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

    protected Location getLocation() {
        return parser.getLocation();
    }

    protected String requireAttribute(Attributes attributes,
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
    protected void requireParent(String myName, String parentName,
                                 Class parentClass)
        throws TagException
    {
        TagUtils.requireParent(this, myName, parentName, parentClass);
    }

    protected Tag findAncestorWithClass(Class tagClass) {
        return TagUtils.findAncestorWithClass(getParent(), tagClass);
    }

    protected Tag requireAncestor(String tagName, Class tagClass)
        throws TagException
    {
        return TagUtils.requireAncestor(getParent(), tagName, tagClass);
    }
}
