/*
 * $Id: ConcatenationExpression.java,v 1.2 2004-10-28 14:04:46 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.List;
import java.util.Map;

/**
 * ConcatenationExpression
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ConcatenationExpression implements Expression {
    private final Expression[] expressions;

    public ConcatenationExpression(Expression[] expressions) {
        if (expressions == null)
            throw new NullPointerException("expressions list is null");
        this.expressions = (Expression[]) expressions.clone();
    }

    public ConcatenationExpression(List expressions) {
        this.expressions =
                (Expression[]) expressions.toArray(Expression.EMPTY_ARRAY);
    }

    public Object evaluate(Map context) throws ExpressionEvaluationException {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < expressions.length; i++) {
            buf.append(expressions[i].evaluate(context).toString());
        }
        return buf.toString();
    }
}
