/*
 * $Id: PropertySetter.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.util.Map;

/**
 * PropertySetter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class PropertySetter {

    private final Class targetClass;

    private Map methods;

    public PropertySetter(Class targetClass) {
        this.targetClass = targetClass;

        getMethodDescriptors();
    }

    private void getMethodDescriptors() {
        
    }

    public void setProperty(String name, String value) {
    }

    /**
     * Convenience method that creates an instance of
     * <code>PropertySetter</code> and uses it to set a property.
     * This is an inefficient approach if you might be setting more than
     * one property.
     *
     * @param object
     * @param name
     * @param value
     */
    public static void setProperty(Object object, String name, String value) {
        new PropertySetter(object.getClass()).setProperty(name, value);
    }
}
