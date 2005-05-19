/*
 * $Id: TagException.java,v 1.3 2005-05-19 11:36:31 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * A <code>TagException</code> can be thrown by a {@link Tag} instance to
 * indicate that a non-fatal error has occurred during parsing. The messages
 * from such errors are collected together and made available through the
 * {@link org.codehaus.tagalog.TagalogParser#parseErrors()} method after
 * the parse finishes. Any exception detail will be lost in the process,
 * although the message and cause will be preserved.
 * <p>
 * Cause is handled internally by this class to support pre J2SE 1.4.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class TagException extends Exception {
    private final Throwable cause;

    /**
     * Construct a tag exception with no details.  The parser will not add
     * a ParseError for a TagException with no detail.
     */
    public TagException() {
        super();
        this.cause = null;
    }

    /**
     * Construct a tag exception with the given message.
     *
     * @param message Message describing the exceptional condition.
     */
    public TagException(String message) {
        super(message);
        this.cause = null;
    }

    /**
     * Construct a tag exception with the given message, attaching the
     * exception that caused the condition.
     *
     * @param message Message describing the exceptional condition.
     * @param cause Exception that caused the condition.
     */
    public TagException(String message, Throwable cause) {
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
