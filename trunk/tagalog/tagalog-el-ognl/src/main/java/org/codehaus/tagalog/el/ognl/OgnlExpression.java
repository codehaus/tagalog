/*
 * $Id: OgnlExpression.java,v 1.1 2004-11-02 15:59:45 mhw Exp $
 */

package org.codehaus.tagalog.el.ognl;

import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionEvaluationException;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class OgnlExpression implements Expression {
    private final Object expression;

    public OgnlExpression(Object expression) {
        this.expression = expression;
    }

    public Object evaluate(Map context) throws ExpressionEvaluationException {
        try {
            return Ognl.getValue(expression, context, context);
        } catch (OgnlException e) {
            throw new ExpressionEvaluationException(e);
        }
    }
}
