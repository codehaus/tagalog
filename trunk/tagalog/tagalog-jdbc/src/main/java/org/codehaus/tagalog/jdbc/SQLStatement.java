/*
 * $Id: SQLStatement.java,v 1.4 2004-01-28 11:29:25 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;

import org.codehaus.plexus.util.StringUtils;

import com.fintricity.jdbc.ProcContext.NameValue;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class SQLStatement implements ProcStatement {
    private String dialect;

    private String sql;

    private QueryType queryType = QueryType.ZERO;

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

    protected void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    protected final QueryType getQueryType() {
        return queryType;
    }

    public String toString() {
        if (dialect != null) {
            return "[" + dialect + "] " + sql;
        } else {
            return sql;
        }
    }

    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        if (prepareAndExecute(catalog, proc, ctx) != null) {
            throw new ProcException("statement created a result set", this);
        }
        return null;
    }

    protected PreparedStatement prepareAndExecute(Catalog catalog, Proc proc,
                                                  ProcContext ctx)
        throws ProcException
    {
        Connection conn;
        PreparedStatement stmt;
        String expandedSql;

        try {
            if (dialect != null && !ctx.getDialect(catalog).equals(dialect)) {
                throw new ProcException(
                    "connection has wrong dialect '"
                    + ctx.getDialect(catalog) + "'", this
                );
            }
            conn = ctx.getConnection(catalog);
            expandedSql = expand(sql, ctx);
            stmt = conn.prepareStatement(expandedSql);
            if (stmt.execute()) {
                return stmt;
            } else {
                stmt.close();
                ctx.returnConnection(conn);
                return null;
            }
        } catch (Exception e) {
            throw new ProcException(e, this);
        }
    }

    private String expand(String sql, ProcContext ctx) {
        Iterator iter = ctx.attributeIterator();
        
        while (iter.hasNext()) {
            NameValue attr = (NameValue) iter.next();
            String pattern = "${" + attr.name + "}";
            sql = StringUtils.replace(sql, pattern, attr.value);
        }
        return sql;
    }
}
