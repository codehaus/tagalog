/*
 * $Id: StatementList.java,v 1.3 2004-11-08 07:23:35 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Iterator;
import java.util.List;

/**
 * <code>StatementList</code> implements basic grouping of statements.
 * The interface implies a two-phase construction process: first
 * statements are added to the group by calling {@link #addStatement},
 * then the list of statements is retrieved by calling
 * {@link #getStatementList}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class StatementList {
    private List statementList = new java.util.ArrayList();

    /**
     * Append a statement to the statement list.
     *
     * @param statement Statement to be added.
     * @throws IllegalStateException If the statement list has already been
     * retrieved through a call to {@link #getStatementList()}.
     */
    public void addStatement(Statement statement) {
        if (statementList == null)
            throw new IllegalStateException("addStatement called after"
                                            +" statement list completed");
        statementList.add(statement);
    }

    /**
     * Retrieve the list of statements. Can only be called once.
     *
     * @return List of statements.
     * @throws IllegalStateException If the statement list has already been
     * retrieved through a call to {@link #getStatementList()}.
     */
    public Statement[] getStatementList() {
        Statement[] statements;

        if (statementList == null)
            throw new IllegalStateException("statement list already retrieved");
        statements = (Statement[]) statementList.toArray(Statement.EMPTY_ARRAY);
        statementList = null;
        return statements;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{ ");
        if (statementList != null) {
            Iterator i = statementList.iterator();
            while (i.hasNext()) {
                Statement stmt = (Statement) i.next();
                buf.append(stmt);
                buf.append("; ");
            }
        } else {
            buf.append("<used> ");
        }
        buf.append('}');
        return buf.toString();
    }
}
