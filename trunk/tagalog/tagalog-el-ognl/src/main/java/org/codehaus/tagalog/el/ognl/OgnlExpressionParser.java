/*
 * $Id: OgnlExpressionParser.java,v 1.2 2005-04-20 15:57:01 mhw Exp $
 */

package org.codehaus.tagalog.el.ognl;

import ognl.Ognl;
import ognl.OgnlException;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.SimpleExpressionParser;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class OgnlExpressionParser extends SimpleExpressionParser {
    public Expression parse(String text) throws ExpressionParseException {
        try {
            Object expression = Ognl.parseExpression(text);
            return new OgnlExpression(expression);
        } catch (OgnlException e) {
            throw new ExpressionParseException(e);
        }
    }
}
