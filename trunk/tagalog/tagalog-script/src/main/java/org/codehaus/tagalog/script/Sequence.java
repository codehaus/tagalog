/*
 * $Id: Sequence.java,v 1.4 2005-04-26 17:04:43 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * A sequence of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class Sequence extends AbstractStatement
    implements Statement
{
    private final Statement[] statements;

    /**
     * @param body The list of statements that will make up the sequence.
     */
    public Sequence(Statement[] body) {
        this.statements = (Statement[]) body.clone();
    }

    public void execute(Map context) throws ScriptException {
        for (int i = 0; i < statements.length; i++) {
            statements[i].execute(context);
        }
    }
}
