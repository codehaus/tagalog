/*
 * $Id: TagBinding.java,v 1.1 2005-04-07 15:49:12 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Immutable value class representing the association between an
 * element name and a {@link Tag} implementation. Instances of this
 * class are used to assemble a {@link TagLibrary} for a particular
 * namespace. Instances can also be used to distinguish between
 * the values created by child tags and passed to the
 * {@link Tag#child} method. Note that such tests are typically
 * written using the <code>==</code> operator: tagalog does not
 * duplicate instances of <code>TagBinding</code>. For example:
 *
 * <pre>
 *     public void child(TagBinding childType, Object child)
 *         throws TagException, TagalogParseException
 *     {
 *         if (childType == PeopleTagLibrary.FIRST_NAME)
 *             person.setFirstName((String) child);
 *         else if (childType == PeopleTagLibrary.LAST_NAME)
 *             person.setLastName((String) child);
 *     }
 * </pre>
 *
 * Care should be taken to ensure that {@link TagLibrary} instances
 * used to parse a single document do not share <code>TagBinding</code>
 * instances. <code>TagBinding</code> provides a {@link #clone}
 * method if it is necessary to copy a <code>TagBinding</code> from
 * an existing tag library.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class TagBinding implements Cloneable {
    private final String elementName;

    private final Class tagClass;

    /**
     * Constructor.
     *
     * @param elementName Element name; must not be <code>null</code>.
     * @param tagClass Tag implementation class; must not be
     *                 <code>null</code>.
     */
    public TagBinding(String elementName, Class tagClass) {
        if (elementName.length() == 0)
            throw new IllegalArgumentException("element name is empty");
        if (!Tag.class.isAssignableFrom(tagClass))
            throw new IllegalArgumentException("class does not implement Tag");
        this.elementName = elementName;
        this.tagClass = tagClass;
    }

    /**
     * @return The element name.
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * @return The {@link Tag} implementation class.
     */
    public Class getTagClass() {
        return tagClass;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("clone failed");
        }
    }
}
