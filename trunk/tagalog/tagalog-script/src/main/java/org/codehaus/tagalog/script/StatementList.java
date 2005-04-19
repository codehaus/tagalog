/*
 * $Id: StatementList.java,v 1.4 2005-04-19 16:25:13 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Iterator;
import java.util.List;

import org.codehaus.tagalog.el.Expression;

/**
 * <code>StatementList</code> implements basic grouping of statements.
 * The interface implies a two-phase construction process: first
 * statements are added to the group by calling {@link #addStatement} and
 * {@link #addExpression}, then the list of statements is retrieved by
 * calling {@link #getStatementList}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
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
            throw new IllegalStateException("statement list already retrieved");
        statementList.add(statement);
    }

    /**
     * Append an {@link Expression} to the statement list by wrapping it
     * in an instance of {@link ExpressionStatement}.
     *
     * @param expression Expression to be added.
     * @throws IllegalStateException If the statement list has already been
     * retrieved through a call to {@link #getStatementList()}.
     */
    public void addExpression(Expression expression) {
        if (statementList == null)
            throw new IllegalStateException("statement list already retrieved");
        statementList.add(new ExpressionStatement(expression));
    }

    public int size() {
        if (statementList == null)
            throw new IllegalStateException("statement list already retrieved");
        return statementList.size();
    }

    /**
     * Convert the statement list into a {@link Statement}.
     * Can only be called once.
     *
     * @return A single {@link Statement} representing the list.
     * @throws IllegalStateException If the statement list has already been
     * retrieved through a call to {@link #getStatement}.
     */
    public Statement getStatement() {
        if (statementList == null)
            throw new IllegalStateException("statement list already retrieved");

        int size = statementList.size();
        Statement result;

        if (size == 0)
            result = Statement.NULL;
        else if (size == 1)
            result = (Statement) statementList.get(0);
        else {
            Statement[] statements;

            statements = (Statement[]) statementList.toArray(Statement.EMPTY_ARRAY);
            result = new Sequence(statements);
        }
        statementList = null;
        return result;
    }

    public Expression getStatementExpression() {
        return new StatementExpression(getStatement());
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
