/*
 * $Id: ExpressionStatement.java,v 1.4 2005-04-19 16:27:01 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionEvaluationException;
import org.codehaus.tagalog.script.AbstractStatement;
import org.codehaus.tagalog.script.Statement;

/**
 * Evaluate an expression and output the result.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public class ExpressionStatement
    extends AbstractStatement
    implements Statement
{
    private final Expression value;

    public ExpressionStatement(Expression value) {
        this.value = value;
    }

    public void execute(Map context) throws Exception {
        Object result = evaluate(context);

        if (result != null)
            output(context, result.toString());
    }

    protected Object evaluate(Map context)
        throws ExpressionEvaluationException
    {
        if (value == null)
            return null;
        return value.evaluate(context);
    }
}
