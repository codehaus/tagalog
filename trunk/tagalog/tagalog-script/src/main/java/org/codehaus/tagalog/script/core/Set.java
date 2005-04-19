/*
 * $Id: Set.java,v 1.2 2005-04-19 16:32:14 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import java.util.Map;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.script.AbstractCompoundStatement;
import org.codehaus.tagalog.script.Statement;

/**
 * Implementation of the <code>set</code> JSTL action.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class Set
    extends AbstractCompoundStatement
    implements Statement
{
    private final String var;

    public Set(String var, Expression value) {
        super(value);
        this.var = var;
    }

    public void execute(Map context) throws Exception {
        Object result = evaluateBody(context);

        if (result != null)
            context.put(var, result);
        else
            context.remove(var);
    }
}
