/*
 * $Id: Sequence.java,v 1.1 2004-03-02 21:45:29 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * A sequence of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class Sequence extends AbstractCompoundStatement
    implements CompoundStatement
{
    public void execute(Map context) throws Exception {
        Statement[] statements = getStatementList();

        for (int i = 0; i < statements.length; i++) {
            statements[i].execute(context);
        }
    }
}
