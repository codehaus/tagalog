/*
 * $Id: Sequence.java,v 1.2 2004-11-08 07:23:35 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * A sequence of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public class Sequence extends AbstractStatement
    implements Statement
{
    private final Statement[] statements;

    /**
     * @param body The list of statements that will make up the sequence.
     */
    public Sequence(StatementList body) {
        this.statements = body.getStatementList();
    }

    public void execute(Map context) throws Exception {
        for (int i = 0; i < statements.length; i++) {
            statements[i].execute(context);
        }
    }
}
