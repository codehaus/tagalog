/*
 * $Id: Proc.java,v 1.1 2004-01-23 15:21:36 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Representation of a procedure, a list of statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class Proc {
    private String name;

    private String connectionName;

    private List statementList = new java.util.ArrayList();

    private ProcStatement[] statements;

    private Set argumentNames = new java.util.HashSet();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void addArgumentName(String argumentName) {
        argumentNames.add(argumentName);
    }

    public void addStatement(ProcStatement statement) {
        if (statementList == null)
            throw new IllegalStateException("addStatement called after"
                                            +" statement list completed");
        statementList.add(statement);
    }

    public void closeStatementList() {
        statements = (ProcStatement[]) statementList.toArray(
                                                    ProcStatement.EMPTY_ARRAY);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append(name);
        buf.append(" { ");
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

    public void execute(Catalog catalog, ProcContext ctx) throws ProcException {
        ctx.setConnectionName(connectionName);
        for (int i = 0; i < statements.length; i++) {
            statements[i].execute(catalog, this, ctx);
        }
    }
}
