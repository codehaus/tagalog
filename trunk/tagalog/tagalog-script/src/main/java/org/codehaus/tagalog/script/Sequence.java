/*
 * $Id: Sequence.java,v 1.3 2005-04-19 16:24:15 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * A sequence of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
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

    public void execute(Map context) throws Exception {
        for (int i = 0; i < statements.length; i++) {
            statements[i].execute(context);
        }
    }
}
