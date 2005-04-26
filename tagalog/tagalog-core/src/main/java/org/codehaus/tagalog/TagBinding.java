/*
 * $Id: TagBinding.java,v 1.2 2005-04-26 14:26:38 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Immutable value class representing the association between a
 * name and a {@link NodeHandler} implementation. Instances of this
 * class are used to assemble a {@link TagLibrary} for a particular
 * namespace, or to handle processing instructions. Instances can also
 * be used to distinguish between the values created by child tags and
 * passed to the {@link Tag#child} method. Note that such tests are
 * typically written using the <code>==</code> operator: tagalog does
 * not duplicate instances of <code>TagBinding</code>. For example:
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
 * @version $Revision: 1.2 $
 */
public final class TagBinding implements Cloneable {
    private final String name;

    private final Class nodeHandlerClass;

    /**
     * Constructor.
     *
     * @param name Binding name; must not be <code>null</code>.
     * @param nodeHandlerClass {@link NodeHandler} implementation class;
     *                         must not be <code>null</code>.
     */
    public TagBinding(String name, Class nodeHandlerClass) {
        if (name.length() == 0)
            throw new IllegalArgumentException("element name is empty");
        if (!NodeHandler.class.isAssignableFrom(nodeHandlerClass))
            throw new IllegalArgumentException("class not NodeHandler");
        this.name = name;
        this.nodeHandlerClass = nodeHandlerClass;
    }

    /**
     * @return The binding name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The {@link NodeHandler} implementation class.
     */
    public Class getNodeHandlerClass() {
        return nodeHandlerClass;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("clone failed");
        }
    }
}
