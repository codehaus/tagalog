/*
 * $Id: ExpressionParseException.java,v 1.2 2004-11-02 14:36:43 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Thrown by {@link org.codehaus.tagalog.el.ExpressionParser#parse(String)} to
 * indicate that an expression could not be parsed.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ExpressionParseException extends Exception {
    public ExpressionParseException(String message) {
        super(message);
    }

    public ExpressionParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create an <code>ExpressionParseException</code> with the default
     * message "expression could not be parsed".
     *
     * @param cause Exception indicating the reason for evaluation failure.
     */
    public ExpressionParseException(Throwable cause) {
        super("expression could not be parsed", cause);
    }
}
