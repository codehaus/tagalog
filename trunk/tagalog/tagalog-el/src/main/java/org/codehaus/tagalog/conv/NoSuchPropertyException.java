/*
 * $Id: NoSuchPropertyException.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Thrown to indicate that a class has no property with a particular name.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class NoSuchPropertyException extends PropertyException {
    private final Class targetClass;
    private final String propertyName;

    /**
     * @param targetClass The class that the property could not be located in.
     * @param propertyName The property name that could not be found.
     */
    public NoSuchPropertyException(Class targetClass, String propertyName) {
        super("no property '" + propertyName + "' in " + targetClass);
        this.targetClass = targetClass;
        this.propertyName = propertyName;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
