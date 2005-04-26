/*
 * $Id: AbstractStatement.java,v 1.4 2005-04-26 17:04:43 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionEvaluationException;

/**
 * Abstract base class for implementations of {@link Statement}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public abstract class AbstractStatement implements Statement {

    protected void output(Map context, String string) throws ScriptException {
        Object o = context.get(Script.TAGALOG_OUT);
        if (o instanceof Writer) {
            try {
                ((Writer) o).write(string);
            } catch (IOException e) {
                throw new ScriptException("output failed", e);
            }
        }
    }

    protected static Object evaluateNotNull(String name, Expression expression,
                                            Map context)
        throws ScriptException
    {
        Object result = evaluate(name, expression, context);
        if (result == null)
            throw new ScriptException(name + " evaluated to null");
        return result;
    }

    protected static Object evaluate(String name, Expression expression,
                                     Map context)
        throws ScriptException
    {
        try {
            return expression.evaluate(context);
        } catch (ExpressionEvaluationException e) {
            throw new ScriptException("evaluation of " + name + " failed", e);
        }
    }
}
