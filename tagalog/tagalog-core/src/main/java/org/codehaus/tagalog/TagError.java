/*
 * $Id: TagError.java,v 1.3 2005-05-18 10:28:40 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * A <code>TagError</code> can be thrown by implementations of the
 * {@link Tag} interface if they encounter a condition that makes it
 * impossible to continue processing.<br>
 * However, this style of processing is discouraged: in the majority of
 * cases it is preferrable for <code>Tag</code>s to throw
 * {@link TagException} as this allows parsing to continue and a better
 * error report to be prepared for the user.
 * <p>
 * Cause is handled internally by this class to support pre J2SE 1.4.
 *
 * @author <a href="mailto:krisb@codehaus.org">Kristopher Brown</a>
 * @version $Revision: 1.3 $ $Date: 2005-05-18 10:28:40 $
 */
public class TagError extends Error {
    private final Throwable cause;

    /**
     * Creates a new instance of TagError with the supplied message.
     * @param message the message
     */
    public TagError(String message) {
        super(message);
        this.cause = null;
    }

    /**
     * Creates a new instance of TagError with the supplied message and cause.
     * @param message the message
     * @param cause the cause
     */
    public TagError(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Returns the cause of this TagError.
     * @return the cause of this TagError
     * @see java.lang.Throwable#getCause()
     */
    public final Throwable getCause() {
        return cause;
    }
}
