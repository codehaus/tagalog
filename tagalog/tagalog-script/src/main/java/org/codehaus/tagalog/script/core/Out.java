/*
 * $Id: Out.java,v 1.3 2005-04-20 10:10:16 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import java.util.Map;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.script.AbstractCompoundStatement;
import org.codehaus.tagalog.script.ScriptUtils;
import org.codehaus.tagalog.script.Statement;

/**
 * Implementation of the <code>out</code> JSTL action.
 *
 * <p>
 * Limitations:
 * <ul>
 *
 * <li>The <code>escapeXml</code> attribute must be a Boolean value
 * computed when the statement is parsed. To fully implement the JSTL
 * specification it should be an expression evaluated when the statement
 * is executed.</li>
 *
 * <li>There is no special treatment for evaluation results that are
 * of type <code>java.io.Reader</code>.</li>
 *
 * </ul>
 * </p>
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class Out
    extends AbstractCompoundStatement
    implements Statement
{
    private final Expression value;

    private final boolean escapeXml;

    public Out(Expression value, Expression defaultValue, boolean escapeXml)
    {
        super(defaultValue);
        this.value = value;
        this.escapeXml = escapeXml;
    }

    public void execute(Map context) throws Exception {
        Object result = null;

        if (value != null)
            result = value.evaluate(context);
        if (result == null)
            result = evaluateBody(context);
        if (result == null)
            result = "";
        output(context, escapeXml? ScriptUtils.escapeXml(result.toString())
                                 : result.toString());
    }
}
