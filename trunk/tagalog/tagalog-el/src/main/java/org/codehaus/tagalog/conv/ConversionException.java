/*
 * $Id: ConversionException.java,v 1.1 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Exception thrown due to conversion validation errors.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ConversionException extends ConverterException {
    /**
     * @param message Reason for the conversion failure.
     */
    public ConversionException(String message) {
        super(message);
    }

}
