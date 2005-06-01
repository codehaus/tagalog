/*
 * $Id: ContextValueExpressionParser.java,v 1.3 2005-06-01 07:07:02 krisb Exp $
 */

package org.codehaus.tagalog.el;

/**
 * ContextValueExpressionParser
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public final class ContextValueExpressionParser extends SimpleExpressionParser {

    public Expression parse(String text) throws ExpressionParseException {
        text = text.trim();
        return new ContextValueExpression(text);
    }
}
