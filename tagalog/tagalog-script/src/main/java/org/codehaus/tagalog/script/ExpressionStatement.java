/*
 * $Id: ExpressionStatement.java,v 1.5 2005-04-26 17:04:43 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

import org.codehaus.tagalog.el.Expression;

/**
 * Evaluate an expression and output the result.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.5 $
 */
public class ExpressionStatement
    extends AbstractStatement
    implements Statement
{
    private final Expression value;

    public ExpressionStatement(Expression value) {
        this.value = value;
    }

    public void execute(Map context) throws ScriptException {
        if (value == null)
            return;

        Object result = evaluate("value", value, context);

        if (result != null)
            output(context, result.toString());
    }
}
