/*
 * $Id: ConverterException.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Exception thrown due to conversion validation errors.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ConverterException extends Exception {
    /**
     * @param message Reason for the conversion failure.
     */
    public ConverterException(String message) {
        super(message);
    }

}
