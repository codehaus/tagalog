/*
 * $Id: NullStatement.java,v 1.1 2005-04-19 16:23:31 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * A {@link Statement} that does nothing.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class NullStatement implements Statement {

    NullStatement() {
    }

    public void execute(Map context) throws Exception {
    }
}
