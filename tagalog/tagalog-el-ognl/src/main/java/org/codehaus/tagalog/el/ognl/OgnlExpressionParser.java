/*
 * $Id: OgnlExpressionParser.java,v 1.1 2004-11-02 15:59:45 mhw Exp $
 */

package org.codehaus.tagalog.el.ognl;

import ognl.Ognl;
import ognl.OgnlException;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.ExpressionParser;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class OgnlExpressionParser implements ExpressionParser {
    public Expression parse(String text) throws ExpressionParseException {
        try {
            Object expression = Ognl.parseExpression(text);
            return new OgnlExpression(expression);
        } catch (OgnlException e) {
            throw new ExpressionParseException(e);
        }
    }
}
