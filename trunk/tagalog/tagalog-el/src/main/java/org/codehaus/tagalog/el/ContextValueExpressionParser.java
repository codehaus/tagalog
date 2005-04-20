/*
 * $Id: ContextValueExpressionParser.java,v 1.2 2005-04-20 15:57:07 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * ContextValueExpressionParser
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class ContextValueExpressionParser extends SimpleExpressionParser {
    public Expression parse(String text, ParseController parseController)
        throws ExpressionParseException
    {
        return parse(text);
    }

    public Expression parse(String text) throws ExpressionParseException {
        text = text.trim();
        return new ContextValueExpression(text);
    }
}
