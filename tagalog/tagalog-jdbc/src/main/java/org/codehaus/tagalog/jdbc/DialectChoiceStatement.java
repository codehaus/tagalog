/*
 * $Id: DialectChoiceStatement.java,v 1.2 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.util.Set;

import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

/**
 * Choose the appropriate matching SQL statement from a set of alternatives.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public class DialectChoiceStatement extends StatementGroup
    implements ProcStatement
{
    public void addStatement(ProcStatement statement) {
        if (statement instanceof SQLStatement) {
            super.addStatement(statement);
        } else {
            throw new IllegalArgumentException(
                "dialect choice can only contain SQL statements"
            );
        }
    }

    public void closeStatementList() {
        super.closeStatementList();
        Set dialects = new java.util.HashSet();
        for (int i = 0; i < statements.length; i++) {
            dialects.add(((SQLStatement) statements[i]).getDialect());
        }
        if (dialects.size() != statements.length) {
            throw new IllegalStateException(
                "dialect choice contains duplicate dialects"
            );
        }
    }

    public String toString() {
        return "choice " + super.toString();
    }

    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        String requiredDialect;
        ProcStatement defaultStatement = null;
        ProcStatement foundStatement = null;

        try {
            requiredDialect = ctx.getDialect(catalog);
        } catch (ComponentLookupException e) {
            throw new ProcException(e);
        }
        for (int i = 0; i < statements.length; i++) {
            SQLStatement stmt = (SQLStatement) statements[i];
            String statementDialect = stmt.getDialect();
            if (statementDialect == null)
                defaultStatement = stmt;
            else if (statementDialect.equals(requiredDialect))
                foundStatement = stmt;
        }
        if (foundStatement != null) {
            return foundStatement.execute(catalog, proc, ctx);
        } else if (defaultStatement != null) {
            return defaultStatement.execute(catalog, proc, ctx);
        } else {
            if (requiredDialect == null) {
                throw new ProcException("no default statement", this);
            } else {
                throw new ProcException(
                    "no statement matching dialect " + requiredDialect, this
                );
            }
        }
    }
}
