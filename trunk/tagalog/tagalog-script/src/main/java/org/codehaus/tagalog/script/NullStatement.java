/*
 * $Id: NullStatement.java,v 1.2 2005-04-26 17:04:43 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * A {@link Statement} that does nothing.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class NullStatement implements Statement {

    NullStatement() {
    }

    public void execute(Map context) throws ScriptException {
    }
}
