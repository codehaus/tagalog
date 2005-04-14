/*
 * $Id: SequenceStatement.java,v 1.5 2005-04-14 13:59:06 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * A sequence of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public class SequenceStatement extends AbstractCompoundStatement {
    public Object execute(Catalog catalog, ProcContext ctx)
        throws ProcException
    {
        ProcStatement[] statements = getStatementList();
        Object result = null;

        for (int i = 0; i < statements.length; i++) {
            Object o = statements[i].execute(catalog, ctx);
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
