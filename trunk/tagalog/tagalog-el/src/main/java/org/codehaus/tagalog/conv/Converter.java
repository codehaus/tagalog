/*
 * $Id: Converter.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Interface for objects that convert {@link String}s into some
 * other type.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface Converter {
    Object convert(String text) throws ConversionException;
}
