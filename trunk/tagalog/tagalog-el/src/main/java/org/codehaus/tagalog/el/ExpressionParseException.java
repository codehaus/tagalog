/*
 * $Id: ExpressionParseException.java,v 1.1 2004-10-26 19:14:34 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * ExpressionParseException
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ExpressionParseException extends Exception {
    public ExpressionParseException(String message) {
        super(message);
    }
}
