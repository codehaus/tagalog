/*
 * $Id: ExpressionStatement.java,v 1.1 2004-11-04 17:55:31 mhw Exp $
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
 * @version $Revision: 1.1 $
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
        output(context, result.toString());
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
