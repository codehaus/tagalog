/*
 * $Id: ConverterManager.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.util.Map;

/**
 * ConverterManager
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class ConverterManager {
    public static final ConverterManager INSTANCE = new ConverterManager();

    public Map converters = new java.util.HashMap();

    public ConverterManager() {
        registerConverter(Boolean.class, new BooleanConverter());

        registerConverter(Integer.class, new IntConverter());
    }

    public void registerConverter(Class resultClass, Converter converter) {
        if (resultClass == null)
            throw new NullPointerException("result class");
        if (converter == null)
            throw new NullPointerException("converter class");
        converters.put(resultClass, converter);
    }

    public Converter getConverter(Class resultClass) {
        return (Converter) converters.get(resultClass);
    }
}
