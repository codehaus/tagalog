/*
 * $Id: AbstractCompoundStatement.java,v 1.2 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public abstract class AbstractCompoundStatement
    extends AbstractProcStatement
    implements CompoundProcStatement
{
    private StatementGroup statementGroup = new StatementGroup();

    public void addStatement(ProcStatement statement) {
        statementGroup.addStatement(statement);
    }

    public void closeStatementList() {
        statementGroup.closeStatementList();
    }

    protected ProcStatement[] getStatementList() {
        return statementGroup.getStatementList();
    }

    public String toString() {
        return statementGroup.toString();
    }

}
