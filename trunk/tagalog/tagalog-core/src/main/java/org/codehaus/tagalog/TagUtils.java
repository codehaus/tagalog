/*
 * $Id: TagUtils.java,v 1.8 2004-11-06 12:46:33 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * Static methods that are of use when writing implementations of the
 * {@link Tag} interface. The signatures and implementations of many of
 * these methods are identical to those in Jelly's <code>TagSupport</code>
 * class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.8 $
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

    public static Tag findAncestorWithClass(Tag from, Class tagClass) {
        while (from != null) {
            if (tagClass.isInstance(from))
                return from;
            from = from.getParent();
        }
        return null;
    }

    public static Tag requireAncestor(Tag from, String tagName, Class tagClass)
        throws TagException
    {
        Tag tag = findAncestorWithClass(from, tagClass);
        if (tag == null) {
            throw new TagException(tagName + " ancestor not found");
        }
        return tag;
    }
}
