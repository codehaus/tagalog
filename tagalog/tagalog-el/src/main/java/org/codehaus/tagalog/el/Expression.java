/*
 * $Id: Expression.java,v 1.1 2004-10-26 19:14:34 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

/**
 * General interface for objects that represent expressions that can be
 * evaluated.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface Expression {
    Expression[] EMPTY_ARRAY = new Expression[0];

    Object evaluate(Map context) throws ExpressionEvaluationException;
}
