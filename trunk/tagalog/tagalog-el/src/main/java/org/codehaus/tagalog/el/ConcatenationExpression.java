/*
 * $Id: ConcatenationExpression.java,v 1.3 2004-12-21 17:51:34 krisb Exp $
 */

package org.codehaus.tagalog.el;

import java.util.List;
import java.util.Map;

/**
 * ConcatenationExpression
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ConcatenationExpression implements Expression {
    private final Expression[] expressions;

    public ConcatenationExpression(Expression[] expressions) {
        if (expressions == null)
            throw new NullPointerException("expressions list is null");
        this.expressions = (Expression[]) expressions.clone();
    }

    /**
     * Creates a new instance of ConcatenationExpression with the supplied list
     * of expressions.
     * @param expressions the list of expressions
     */
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
