/*
 * $Id: TagUtils.java,v 1.15 2005-05-26 21:43:39 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Static methods that are of use when writing implementations of the
 * {@link NodeHandler}, {@link Tag} and {@link PIHandler}
 * interfaces.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.15 $
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
     * throws <code>TagException</code> using the supplied <code>tag</code>'s
     * name and <code>attributeName</code> to construct a meaningful error
     * message.
     *
     * <p>
     * This method is typically called through the
     * {@link AbstractTag#requireAttribute(Attributes, String)}
     * convenience method.
     *
     * @param tag Tag for which the attribute is required
     * @param attributes Attributes to check for attribute presence
     * @param attributeName String name of the attribute that must be present
     * @return the value of the attribute if present
     * @throws TagException if an attribute of the supplied name is not present
     */
    public static String requireAttribute(Tag tag,
                                          Attributes attributes,
                                          String attributeName)
        throws TagException
    {
        String value = attributes.getValue(attributeName);
        if (value == null) {
            throw new TagException("attribute '" + attributeName + "'"
                                   + " required on <" + tag.getName() + ">");
        }
        return value;
    }

    // Some of the checks below search for objects in the node handler stack
    // that are instances of a given Class. In the general case we need
    // to use Class.isInstance(Object), but profiling has shown this to
    // be quite an expensive test. There is a significant performance
    // benefit to be had from short-cutting the common case of searching
    // for an object that matches an exact class. So instead of
    //     if (ancestorTagClass.isInstance(parentTag))
    // write
    //     if (ancestorTagClass == parentTag.getClass()
    //         || ancestorTagClass.isInstance(parentTag))

    //
    // Checking node handler's parent.
    //

    /**
     * Assert that a node handler's parent is of a given class, allowing nesting
     * relationships to be enforced. If the parent node handler is not of the
     * specified class the method throws <code>TagException</code> using
     * the <code>childName</code> and <code>parentName</code> parameters to
     * construct a meaningful error message.
     *
     * <p>
     * This method is typically called through the
     * {@link AbstractTag#requireParent(String, String, Class)}
     * convenience method.
     *
     * @param child Node handler to check parent of.
     * @param childName String name of this element.
     * @param parentName String name of the parent element.
     * @param parentNodeHandlerClass Class that the parent node handler
     *                               must match.
     * @throws TagException If the parent tag is not of the required type.
     */
    public static void requireParent(NodeHandler child, String childName,
                                     String parentName,
                                     Class parentNodeHandlerClass)
        throws TagException
    {
        NodeHandler parent = child.getParent();

        // short-cut the common case before using isInstance. see above
        if (!(parentNodeHandlerClass == parent.getClass()
              || parentNodeHandlerClass.isInstance(parent))) {
            throw new TagException("<" + childName + "> must be a child of"
                                   + " <" + parentName + ">");
        }
    }

    //
    // Searching for ancestors.
    //

    /**
     * Returns the first ancestor of the supplied node handler that matches the
     * supplied class.  If no match is found, then <code>null</code> is
     * returned. This is the analogue of the <code>findAncestorWithClass</code>
     * method from Jelly's <code>TagSupport</code> class.
     *
     * @param child The node handler to find the ancestor from. Must not be
     *              <code>null</code>.
     * @param ancestorClass The class of the ancestor node handler to find.
     *                      Must not be <code>null</code>.
     * @return The first ancestor of the supplied node handler that matches the
     * supplied class.  If no match is found, then <code>null</code> is
     * returned.
     * @throws NullPointerException If either argument is <code>null</code>.
     */
    public static NodeHandler findAncestor(NodeHandler child,
                                           Class ancestorClass)
    {
        if (ancestorClass == null)
            throw new NullPointerException("ancestor class is null");
        NodeHandler parent = child.getParent();
        while (parent != null) {
            // short-cut the common case before using isInstance. see above
            if (ancestorClass == parent.getClass()
                || ancestorClass.isInstance(parent))
                return parent;
            parent = parent.getParent();
        }
        return null;
    }

    /**
     * Returns the first ancestor of the supplied node handler that matches the
     * supplied class, raising an exception if no match is found.
     *
     * @param child The tag to find the ancestor from.
     * @param tagName The tag name of the ancestor to find.
     * @param ancestorClass The class of the ancestor to find.
     * @return the first ancestor of the supplied tag that matches the
     * supplied class.
     * @throws TagException If no ancestor is found.
     * @throws NullPointerException If any supplied arguments are
     * <code>null</code>.
     */
    public static NodeHandler requireAncestor(NodeHandler child, String tagName,
                                              Class ancestorClass)
        throws TagException
    {
        NodeHandler tag = findAncestor(child, ancestorClass);
        if (tag == null) {
            throw new TagException(tagName + " ancestor not found");
        }
        return tag;
    }

    //
    // 'Smart' trimming of XML text content
    //

    /**
     * @param object
     * @return
     */
    public static Object trim(String content) {
        if (content == null)
            return null;
        if (content.length() == 0)
            return content;

        TextScanner scanner = new TextScanner(content);

        int lines = scanner.getLineCount();
        if (lines == 0)
            return "";
        else if (lines == 1) {
            return scanner.getLine(0);
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < lines; i++) {
            if (i > 0 && i < lines - 1)
                buffer.append('\n');
            scanner.appendLine(buffer, i);
        }

        return buffer.toString();
    }
}
