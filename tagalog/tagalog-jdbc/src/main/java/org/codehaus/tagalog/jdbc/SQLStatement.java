/*
 * $Id: SQLStatement.java,v 1.1 2004-01-23 15:21:36 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class SQLStatement implements ProcStatement {
    private String dialect;

    private String sql;

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDialect() {
        return dialect;
    }

    public void setSQL(String sql) {
        this.sql = sql;
    }

    public String getSQL() {
        return sql;
    }

    public String toString() {
        return sql;
    }

    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        Connection conn;
        Statement stmt;

        try {
            if (dialect != null && !ctx.getDialect(catalog).equals(dialect))
                return null;
            conn = ctx.getConnection(catalog);
            stmt = conn.prepareStatement(sql);
            return null;
        } catch (Exception e) {
            throw new ProcException(e);
        }
    }
}
