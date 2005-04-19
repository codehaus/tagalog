/*
 * $Id: Remove.java,v 1.1 2005-04-19 16:40:02 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import java.util.Map;

import org.codehaus.tagalog.script.Statement;

/**
 * Implementation of the <code>remove</code> JSTL action.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class Remove implements Statement {

    private final String var;

    public Remove(String var) {
        this.var = var;
    }

    public void execute(Map context) throws Exception {
        context.remove(var);
    }
}
