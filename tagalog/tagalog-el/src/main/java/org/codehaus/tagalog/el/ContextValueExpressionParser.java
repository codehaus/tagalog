/*
 * $Id: ContextValueExpressionParser.java,v 1.1 2004-10-26 19:14:34 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * ContextValueExpressionParser
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class ContextValueExpressionParser implements ExpressionParser {
    public Expression parse(String text) throws ExpressionParseException {
        text = text.trim();
        return new ContextValueExpression(text);
    }
}
