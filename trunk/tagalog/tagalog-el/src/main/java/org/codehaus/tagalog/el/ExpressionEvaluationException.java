/*
 * $Id: ExpressionEvaluationException.java,v 1.2 2004-11-02 14:36:43 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Thrown by {@link org.codehaus.tagalog.el.Expression#evaluate(Map)} to
 * indicate that expression evaluation failed.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ExpressionEvaluationException extends Exception {
    public ExpressionEvaluationException(String message) {
        super(message);
    }

    public ExpressionEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create an <code>ExpressionEvaluationException</code> with the default
     * message "expression evaluation failed".
     *
     * @param cause Exception indicating the reason for evaluation failure.
     */
    public ExpressionEvaluationException(Throwable cause) {
        super("expression evaluation failed", cause);
    }
}
