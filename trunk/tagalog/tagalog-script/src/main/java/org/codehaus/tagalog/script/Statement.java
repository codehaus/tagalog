/*
 * $Id: Statement.java,v 1.2 2005-04-19 16:23:31 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * Basic unit of execution in a {@link Script}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public interface Statement {
    Object[] EMPTY_ARRAY = new Statement[0];

    /**
     * A statement that does nothing when evaluated.
     */
    Statement NULL = new NullStatement();

    /**
     * Execute a statement.
     *
     * @param context Context for this invocation, carrying attribute
     * values.
     * @throws Exception if something goes wrong.
     */
    void execute(Map context) throws Exception;
}
