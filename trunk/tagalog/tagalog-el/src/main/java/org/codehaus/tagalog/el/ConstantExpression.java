/*
 * $Id: ConstantExpression.java,v 1.1 2004-10-26 19:14:34 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

/**
 * An {@link org.codehaus.tagalog.el.Expression} that evaluates to a
 * constant value supplied when the expression is constructed.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ConstantExpression implements Expression {
    private final Object value;

    public ConstantExpression(Object value) {
        this.value = value;
    }

    public Object evaluate(Map context) {
        return value;
    }
}
