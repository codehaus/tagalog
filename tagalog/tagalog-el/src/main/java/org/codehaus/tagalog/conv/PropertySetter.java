/*
 * $Id: PropertySetter.java,v 1.3 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * PropertySetter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public final class PropertySetter {

    private static final String SETTER_PREFIX = "set";
    private static final int SETTER_PREFIX_LENGTH = SETTER_PREFIX.length();

    private final ConverterManager converterManager;

    private final Class targetClass;

    private Map propertyInformation;

    public PropertySetter(Class targetClass, ConverterManager converterManager)
    {
        this.targetClass = targetClass;
        this.converterManager = converterManager;
        this.propertyInformation = new HashMap();

        getMethodDescriptors();
    }

    public PropertySetter(Class targetClass) {
        this(targetClass, ConverterManager.INSTANCE);
    }

    private void getMethodDescriptors() {
        Method[] methods = targetClass.getMethods();

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            String methodName = method.getName();
            if (!methodName.startsWith(SETTER_PREFIX))
                continue;
            Class[] methodParameterTypes = method.getParameterTypes();
            if (methodParameterTypes.length != 1)
                continue;
            String propertyName = propertyName(methodName);
            PropertyInfo info;
            info = (PropertyInfo) propertyInformation.get(propertyName);
            if (info == null) {
                info = new PropertyInfo(method, methodParameterTypes[0]);
                propertyInformation.put(propertyName, info);
            } else {
                info.addSetMethod(method, methodParameterTypes[0]);
            }
        }
    }

    private String propertyName(String methodName) {
        return Introspector.decapitalize(
                                 methodName.substring(SETTER_PREFIX_LENGTH));
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Class getPropertyClass(String name) {
        PropertyInfo info
                        = (PropertyInfo) propertyInformation.get(name);
        return (info == null)? null : info.getPropertyType();
    }

    public void setProperty(Object o, String name, String value)
        throws PropertyException, ConverterException, InvocationTargetException
    {
        PropertyInfo info = (PropertyInfo) propertyInformation.get(name);
        if (info == null)
            throw new NoSuchPropertyException(targetClass, name);
        Class propertyType = info.getPropertyType();
        if (propertyType == String.class) {
            setProperty(o, info.getMainSetMethod(), value);
            return;
        }
        Converter converter = converterManager.getConverter(propertyType);
        if (converter == null)
            throw new NoSuchConverterException(propertyType);
        Object convertedValue = converter.convert(value);
        setProperty(o, info.getMainSetMethod(), convertedValue);
    }

    private void setProperty(Object o, Method setMethod, Object value)
        throws InvocationTargetException, PropertySetterException
    {
        try {
            setMethod.invoke(o, new Object[] { value });
        } catch (IllegalArgumentException e) {
            throw new PropertySetterException("caught unexpected exception", e);
        } catch (IllegalAccessException e) {
            throw new PropertySetterException("caught unexpected exception", e);
        }
    }
}
