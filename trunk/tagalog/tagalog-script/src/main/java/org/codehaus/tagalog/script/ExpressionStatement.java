/*
 * $Id: ExpressionStatement.java,v 1.2 2005-03-31 21:34:18 mhw Exp $
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
 * @version $Revision: 1.2 $
 */
public class ExpressionStatement
    extends AbstractStatement
    implements Statement
{
    private final Expression value;

    private final boolean escapeXml;

    public ExpressionStatement(Expression value) {
        this(value, true);
    }

    public ExpressionStatement(Expression value, boolean escapeXml) {
        this.value = value;
        this.escapeXml = escapeXml;
    }

    public void execute(Map context) throws Exception {
        Object result = evaluate(context);

        if (result == null)
            result = "";
        output(context, escapeXml? ScriptUtils.escapeXml(result.toString())
                                 : result.toString());
    }

    protected Object evaluate(Map context)
        throws ExpressionEvaluationException
    {
        if (value != null)
            return value.evaluate(context);
        else
            return null;
    }
}
