/*
 * $Id: Statement.java,v 1.1 2004-03-02 21:45:29 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * Basic unit of execution in a {@link Script}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public interface Statement {
    Object[] EMPTY_ARRAY = new Statement[0];

    /**
     * Execute a statement.
     *
     * @param context Context for this invocation, carrying attribute
     * values.
     * @throws Exception if something goes wrong.
     */
    void execute(Map context) throws Exception;
}
