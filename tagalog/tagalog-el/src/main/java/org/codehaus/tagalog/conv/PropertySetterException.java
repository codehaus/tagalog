/*
 * $Id: PropertySetterException.java,v 1.1 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Represents an exceptional condition in the setting of properties.
 *
 * @author <a href="mailto:krisb@codehaus.org">Kristopher Brown</a>
 * @version $Revision: 1.1 $
 */
public class PropertySetterException extends PropertyException {

    /**
     * Creates a new instance of PropertySetterException with the supplied
     * message and underlying cause.
     * @param message the message of this Exception
     * @param cause the underlying cause of this Exception
     */
    public PropertySetterException(String message, Throwable cause) {
        super(message, cause);
    }
}
