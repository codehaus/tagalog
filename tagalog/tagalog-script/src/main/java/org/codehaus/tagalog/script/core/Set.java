/*
 * $Id: Set.java,v 1.1 2005-04-05 17:14:01 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import java.util.Map;

import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.script.Statement;

/**
 * Set
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class Set implements Statement {

    private final Expression value;

    private final String var;

    public Set(String var, Expression value) {
        this.var = var;
        this.value = value;
    }

    public void execute(Map context) throws Exception {
        Object result = (value == null)? null : value.evaluate(context);

        if (result != null)
            context.put(var, result);
        else
            context.remove(var);
    }
}
