/*
 * $Id: Converter.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Interface for objects that convert {@link String}s into some
 * other type.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface Converter {
    Object convert(String text) throws ConverterException;
}
