/*
 * $Id: TagUtils.java,v 1.10 2004-11-15 14:21:34 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Static methods that are of use when writing implementations of the
 * {@link Tag} interface.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.10 $
 */
public final class TagUtils {
    /**
     * Private constructor to prevent instantiation.
     */
    private TagUtils() {
    }

    //
    // Retrieving attributes.
    //

    /**
     * Asserts that the supplied <code>attributes</code> contains an attribute
     * with a name matching the supplied <code>attributeName</code>.  Returns
     * the value of the attribute if there is a match, otherwise, the method
     * throws <code>TagException</code> using the <code>elementName</code> and
     * <code>attributeName</code> parameters to construct a meaningful error
     * message.
     *
     * <p>
     * This method is typically called through the
     * {@link AbstractTag#requireAttribute(Attributes, String, String)}
     * convenience method.
     *
     * @param attributes Attributes to check for attribute presence
     * @param elementName String name of the element containing the attributes
     * @param attributeName String name of the attribute that must be present
     * @return the value of the attribute if present
     * @throws TagException if an attribute of the supplied name is not present
     */
    public static String requireAttribute(Attributes attributes,
                                          String elementName,
                                          String attributeName)
        throws TagException
    {
        String value = attributes.getValue(attributeName);
        if (value == null) {
            throw new TagException("attribute '" + attributeName + "'"
                                   + " required on <" + elementName + ">");
        }
        return value;
    }

    //
    // Checking tag's parent.
    //


    /**
     * Assert that a tag's parent is of a given class, allowing nesting
     * relationships to be enforced. If the parent tag is not of the
     * specified class the method throws <code>TagException</code> using
     * the <code>childName</code> and <code>parentName</code> parameters to
     * construct a meaningful error message.
     *
     * <p>
     * This method is typically called through the
     * {@link AbstractTag#requireParent(String, String, Class)}
     * convenience method.
     *
     * @param child Tag to check parent of
     * @param childName String name of this element.
     * @param parentName String name of the parent element
     * @param parentClass Class that the parent tag must match.
     * @throws TagException If the parent tag is not of the required type.
     */
    public static void requireParent(Tag child, String childName,
                                     String parentName, Class parentClass)
        throws TagException
    {
        if (!(parentClass.isInstance(child.getParent()))) {
            throw new TagException("<" + childName + "> must be a child of"
                                   + " <" + parentName + ">");
        }
    }

    //
    // Searching for ancestors.
    //

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
    public static Tag findAncestor(Tag childTag, Class ancestorTagClass) {
        if (ancestorTagClass == null)
            throw new NullPointerException("ancestor tag class is null");
        Tag parentTag = childTag.getParent();
        while (parentTag != null) {
            if (ancestorTagClass.isInstance(parentTag))
                return parentTag;
            parentTag = parentTag.getParent();
        }
        return null;
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
     * @throws NullPointerException If any supplied arguments are
     * <code>null</code>.
     */
    public static Tag requireAncestor(Tag childTag, String tagName,
                                      Class ancestorTagClass)
        throws TagException
    {
        Tag tag = findAncestor(childTag, ancestorTagClass);
        if (tag == null) {
            throw new TagException(tagName + " ancestor not found");
        }
        return tag;
    }
}
