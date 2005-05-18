/*
 * $Id: ExpressionEvaluationException.java,v 1.3 2005-05-18 10:28:42 krisb Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Thrown by {@link org.codehaus.tagalog.el.Expression#evaluate(Map)} to
 * indicate that expression evaluation failed.
 * <p>
 * Cause is handled internally by this class to support pre J2SE 1.4.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ExpressionEvaluationException extends Exception {
    private final Throwable cause;

    public ExpressionEvaluationException(String message) {
        super(message);
        this.cause = null;
    }

    public ExpressionEvaluationException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    /**
     * Create an <code>ExpressionEvaluationException</code> with the default
     * message "expression evaluation failed".
     *
     * @param cause Exception indicating the reason for evaluation failure.
     */
    public ExpressionEvaluationException(Throwable cause) {
        super("expression evaluation failed");
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
