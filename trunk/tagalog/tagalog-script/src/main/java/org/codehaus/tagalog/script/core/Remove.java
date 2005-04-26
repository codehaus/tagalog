/*
 * $Id: Remove.java,v 1.3 2005-04-26 17:04:43 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import java.util.Map;

import org.codehaus.tagalog.script.ScriptException;
import org.codehaus.tagalog.script.Statement;

/**
 * Implementation of the <code>remove</code> JSTL action.
 *
 * <p>
 * Limitations:
 * <ul>
 *
 * <li>The <code>scope</code> attribute is silently ignored.</li>
 *
 * </ul>
 * </p>
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class Remove implements Statement {

    private final String var;

    public Remove(String var) {
        this.var = var;
    }

    public void execute(Map context) throws ScriptException {
        context.remove(var);
    }
}
