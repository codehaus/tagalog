/*
 * $Id: ConverterManager.java,v 1.2 2005-03-01 10:32:56 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.util.Map;

/**
 * Manages a mapping from classes to {@link Converter}s.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class ConverterManager {
    public static final ConverterManager INSTANCE = new ConverterManager();

    public Map converters = new java.util.HashMap();

    public ConverterManager() {
        registerConverter(Boolean.class, new BooleanConverter());
        registerConverter(Byte.class, new ByteConverter());
        registerConverter(Short.class, new ShortConverter());
        registerConverter(Integer.class, new IntegerConverter());
        registerConverter(Long.class, new LongConverter());
    }

    public void registerConverter(Class resultClass, Converter converter) {
        if (resultClass == null)
            throw new NullPointerException("result class");
        if (converter == null)
            throw new NullPointerException("converter class");
        converters.put(resultClass, converter);
    }

    public Converter getConverter(Class resultClass) {
        if (resultClass.isPrimitive())
            resultClass = (Class) canonicalMap.get(resultClass);
        return (Converter) converters.get(resultClass);
    }

    private static Map canonicalMap = new java.util.HashMap();

    static {
        canonicalMap.put(Void.TYPE, Void.class);
        canonicalMap.put(Boolean.TYPE, Boolean.class);
        canonicalMap.put(Byte.TYPE, Byte.class);
        canonicalMap.put(Character.TYPE, Character.class);
        canonicalMap.put(Short.TYPE, Short.class);
        canonicalMap.put(Integer.TYPE, Integer.class);
        canonicalMap.put(Long.TYPE, Long.class);
        canonicalMap.put(Float.TYPE, Float.class);
        canonicalMap.put(Double.TYPE, Double.class);
    }

    public static Class canonicalClass(Class c) {
        if (!c.isPrimitive())
            return c;
        return (Class) canonicalMap.get(c);
    }
}
