/*
 * $Id: StatementGroup.java,v 1.6 2005-04-14 09:25:59 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.util.Iterator;
import java.util.List;

/**
 * <code>StatementGroup</code> implements basic grouping of statements.
 * The interface implies a two-phase construction process: first
 * statements are add to the group by calling {@link #addStatement},
 * then the list of statements is closed by calling
 * {@link #closeStatementList}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
 */
public final class StatementGroup {
    private List statementList = new java.util.ArrayList();

    private ProcStatement[] statements;

    public void addStatement(ProcStatement statement) {
        if (statementList == null)
            throw new IllegalStateException("addStatement called after"
                                            +" statement list completed");
        statementList.add(statement);
    }

    public void closeStatementList() {
        if (statementList == null)
            throw new IllegalStateException("statement list already closed");
        statements = (ProcStatement[]) statementList.toArray(
                                                    ProcStatement.EMPTY_ARRAY);
        statementList = null;
    }

    public ProcStatement[] getStatementList() {
        return (ProcStatement[]) statements.clone();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{ ");
        if (statementList != null) {
            Iterator i = statementList.iterator();
            while (i.hasNext()) {
                ProcStatement stmt = (ProcStatement) i.next();
                buf.append(stmt);
                buf.append("; ");
            }
        } else if (statements != null) {
            for (int i = 0; i < statements.length; i++) {
                buf.append(statements[i]);
                buf.append("; ");
            }
        }
        buf.append('}');
        return buf.toString();
    }
}
