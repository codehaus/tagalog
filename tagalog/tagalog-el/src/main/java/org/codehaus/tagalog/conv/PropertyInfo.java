/*
 * $Id: PropertyInfo.java,v 1.1 2005-03-01 10:34:34 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.lang.reflect.Method;

/**
 * Collection of information about an objects properties.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
final class PropertyInfo {
    private Class propertyType;
    private Method mainSetMethod;
    private Method[] alternateSetMethods;

    public PropertyInfo(Method setMethod, Class parameterClass) {
        this.propertyType = parameterClass;
        this.mainSetMethod = setMethod;
    }

    public Class getPropertyType() {
        return propertyType;
    }

    public Method getMainSetMethod() {
        return mainSetMethod;
    }

    public void addSetMethod(Method setMethod, Class parameterClass) {
        if (parameterClass == String.class) {
            Class mainMethodParamClass = mainSetMethod.getParameterTypes()[0];
            if (mainMethodParamClass == String.class)
                duplicate(setMethod, mainSetMethod);
            addAlternateMethod(mainSetMethod, mainMethodParamClass);
            propertyType = String.class;
            mainSetMethod = setMethod;
        } else if (parameterClass == propertyType) {
            duplicate(setMethod, mainSetMethod);
        } else {
            addAlternateMethod(setMethod, parameterClass);
        }
    }

    private void addAlternateMethod(Method setMethod, Class parameterClass) {
        int length;

        if (alternateSetMethods == null)
            length = 0;
        else
            length = alternateSetMethods.length;
        Method[] newMethods = new Method[length + 1];
        for (int i = 0; i < length; i++) {
            Method existingMethod = alternateSetMethods[i];
            if (existingMethod.getParameterTypes()[0] == parameterClass)
                duplicate(setMethod, existingMethod);
            newMethods[i] = existingMethod;
        }
        newMethods[length] = setMethod;
        alternateSetMethods = newMethods;
    }

    private void duplicate(Method methodOne, Method methodTwo) {
        throw new IllegalStateException(methodOne.toString()
                                        + " duplicates "
                                        + methodTwo.toString());
    }
}
