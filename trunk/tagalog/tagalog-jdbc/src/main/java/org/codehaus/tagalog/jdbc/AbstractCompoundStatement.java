/*
 * $Id: AbstractCompoundStatement.java,v 1.1 2004-02-26 12:27:49 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
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
