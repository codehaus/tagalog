/*
 * $Id: SequenceStatement.java,v 1.4 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * A sequence of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class SequenceStatement extends AbstractCompoundStatement {
    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        ProcStatement[] statements = getStatementList();
        Object result = null;

        for (int i = 0; i < statements.length; i++) {
            Object o = statements[i].execute(catalog, proc, ctx);
            if (o != null) {
                if (result instanceof DiscardableProcResult) {
                    ((DiscardableProcResult) result).discard();
                }
                result = o;
            }
        }
        return result;
    }
}
