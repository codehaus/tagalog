/*
 * $Id: StatementGroup.java,v 1.3 2004-02-11 19:02:52 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class implementing basic grouping of statements.
 * The interface implies a two-phase construction process: first
 * statements are add to the group by calling {@link #addStatement},
 * then the list of statements is closed by calling
 * {@link #closeStatementList}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public abstract class StatementGroup implements ProcStatement {
    private List statementList = new java.util.ArrayList();

    protected ProcStatement[] statements;

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
