/*
 * $Id: ConverterException.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Represents an exceptional condition in the handling of converters.
 * <p>
 * Cause is handled internally by this class to support pre J2SE 1.4.
 *
 * @author <a href="mailto:krisb@codehaus.org">Kristopher Brown</a>
 * @version $Revision: 1.2 $
 */
public abstract class ConverterException extends Exception {
    private final Throwable cause;

    /**
     * Creates a new instance of ConverterException with the supplied message.
     * @param message the message of this Exception
     */
    public ConverterException(String message) {
        super(message);
        this.cause = null;
    }

    /**
     * Creates a new instance of ConverterException with the supplied message
     * and underlying cause.
     * @param message the message of this Exception
     * @param cause the underlying cause of this Exception
     */
    public ConverterException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Returns the cause of this Exception.
     * @return the cause of this Exception
     * @see java.lang.Throwable#getCause()
     */
    public final Throwable getCause() {
        return cause;
    }
}
