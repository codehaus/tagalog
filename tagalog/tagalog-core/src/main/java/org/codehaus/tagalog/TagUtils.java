/*
 * $Id: TagUtils.java,v 1.2 2004-02-11 17:27:54 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * <code>TagUtils</code> contains static methods that are of use when
 * writing implementations of the {@link Tag} interface. The signatures
 * and implementations of many of these methods are identical to those
 * in Jelly's <code>TagSupport</code> class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
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

    public static String requireAttribute(String attributeName,
                                          Attributes attributes)
        throws TagalogParseException
    {
        String value = attributes.getValue(attributeName);
        if (value == null) {
            throw new TagalogParseException(attributeName
                                            + " attribute not found");
        }
        return value;
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
        throws TagalogParseException
    {
        Tag tag = findAncestorWithClass(from, tagClass);
        if (tag == null) {
            throw new TagalogParseException(tagName + " ancestor not found");
        }
        return tag;
    }
}
