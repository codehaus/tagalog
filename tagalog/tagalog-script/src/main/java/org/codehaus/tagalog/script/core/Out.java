/*
 * $Id: Out.java,v 1.1 2004-11-04 17:56:02 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import java.util.Map;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionEvaluationException;
import org.codehaus.tagalog.script.ExpressionStatement;
import org.codehaus.tagalog.script.Statement;

/**
 * Evaluate an expression and output the result, or a default value.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class Out extends ExpressionStatement implements Statement {
    private final Expression defaultValue;

    public Out(Expression value) {
        this(value, null, true);
    }

    public Out(Expression value, Expression defaultValue, boolean escapeXml) {
        super(value, escapeXml);
        this.defaultValue = defaultValue;
    }

    protected Object evaluate(Map context)
        throws ExpressionEvaluationException
    {
        Object result = super.evaluate(context);

        if (result == null && defaultValue != null)
            result = defaultValue.evaluate(context);
        return result;
    }
}
