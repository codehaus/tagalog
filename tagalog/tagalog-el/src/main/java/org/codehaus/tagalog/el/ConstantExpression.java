/*
 * $Id: ConstantExpression.java,v 1.2 2004-12-21 17:52:21 krisb Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

/**
 * An {@link org.codehaus.tagalog.el.Expression} that evaluates to a
 * constant value supplied when the expression is constructed.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class ConstantExpression implements Expression {
    private final Object value;

    public ConstantExpression(Object value) {
        this.value = value;
    }

    public Object evaluate(Map context) {
        return value;
    }
}
