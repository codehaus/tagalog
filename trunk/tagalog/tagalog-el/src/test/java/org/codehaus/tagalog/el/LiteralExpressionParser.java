/*
 * $Id: LiteralExpressionParser.java,v 1.1 2005-04-20 15:57:07 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Simple expression parser that returns the literal text of expressions.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class LiteralExpressionParser extends SimpleExpressionParser {
    public Expression parse(String text) throws ExpressionParseException {
        return new ConstantExpression(text);
    }
}
