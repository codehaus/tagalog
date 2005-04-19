/*
 * $Id: Out.java,v 1.2 2005-04-19 16:32:14 mhw Exp $
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
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
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
