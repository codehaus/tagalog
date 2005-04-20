/*
 * $Id: Expression.java,v 1.2 2005-04-20 11:10:13 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

/**
 * General interface for objects that represent expressions that can be
 * evaluated.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface Expression {
    Expression[] EMPTY_ARRAY = new Expression[0];

    /**
     * Constant <code>Expression</code> that evaluates to <code>null</code>.
     */
    Expression NULL = new ConstantExpression(null);

    /**
     * Constant <code>Expression</code> that evaluates to the empty
     * {@link String}.
     */
    Expression EMPTY_STRING = new ConstantExpression("");

    Object evaluate(Map context) throws ExpressionEvaluationException;
}
