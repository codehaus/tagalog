/*
 * $Id: ExpressionParseException.java,v 1.3 2005-05-18 10:28:42 krisb Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Thrown by {@link org.codehaus.tagalog.el.ExpressionParser#parse(String)} to
 * indicate that an expression could not be parsed.
 * <p>
 * Cause is handled internally by this class to support pre J2SE 1.4.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ExpressionParseException extends Exception {
    private final Throwable cause;

    public ExpressionParseException(String message) {
        super(message);
        this.cause = null;
    }

    public ExpressionParseException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Create an <code>ExpressionParseException</code> with the default
     * message "expression could not be parsed".
     *
     * @param cause Exception indicating the reason for evaluation failure.
     */
    public ExpressionParseException(Throwable cause) {
        super("expression could not be parsed");
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
