/*
 * $Id: ExpressionEvaluationException.java,v 1.1 2004-10-26 19:14:34 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * ExpressionEvaluationException
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ExpressionEvaluationException extends Exception {
    public ExpressionEvaluationException(String message) {
        super(message);
    }

    public ExpressionEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }
}
