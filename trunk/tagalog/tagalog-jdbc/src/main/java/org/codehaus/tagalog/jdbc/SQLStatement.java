/*
 * $Id: SQLStatement.java,v 1.7 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.7 $
 */
public class SQLStatement implements ProcStatement {
    private String dialect;

    private QueryType queryType = QueryType.ZERO;

    private String sqlTemplate;

    private BindVariable[] bindVariables;

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDialect() {
        return dialect;
    }

    protected void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    protected final QueryType getQueryType() {
        return queryType;
    }

    public void setSQLTemplate(String sql) {
        if (sql == null) {
            throw new NullPointerException("sql is null");
        }
        if (bindVariables != null) {
            throw new IllegalStateException(
                "attempt to change sql after it has been used"
            );
        }
        this.sqlTemplate = sql;
    }

    private synchronized String getSQLTemplate() {
        if (bindVariables == null) {
            parseSQLForBindVariables();
        }
        return sqlTemplate;
    }

    private synchronized BindVariable[] getBindVariables() {
        if (bindVariables == null) {
            parseSQLForBindVariables();
        }
        return bindVariables;
    }

    private void parseSQLForBindVariables() {
        StringBuffer newSQL = new StringBuffer();
        ArrayList variableList = new ArrayList();
        int start = 0;
        int open, close;
        String var;

        while ((open = sqlTemplate.indexOf("?{", start)) != -1) {
            if ((close = sqlTemplate.indexOf('}', open + 2)) == -1)
                break;  // no closing brace
            if (close == open + 2)
                break;  // ?{}
            newSQL.append(sqlTemplate.substring(start, open + 1));
            var = sqlTemplate.substring(open + 2, close);
            variableList.add(new BindVariable(var));
            start = close + 1;
        }
        if (start != 0) {
            newSQL.append(sqlTemplate.substring(start));
            sqlTemplate = newSQL.toString();

            bindVariables = (BindVariable[])
                variableList.toArray(BindVariable.EMPTY_ARRAY);
            Arrays.sort(bindVariables, new BindVariableComparator());
        } else {
            bindVariables = BindVariable.EMPTY_ARRAY;
        }
    }

    public String toString() {
        if (dialect != null) {
            return "[" + dialect + "] " + getSQLTemplate();
        } else {
            return getSQLTemplate();
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
            expandedSql = expand(getSQLTemplate(), ctx);
            stmt = conn.prepareStatement(expandedSql);
            bindParameters(stmt, ctx);
            if (stmt.execute()) {
                return stmt;
            } else {
                stmt.close();
                ctx.returnConnection(conn);
                return null;
            }
        } catch (ProcException e) {
            throw e;
        } catch (Exception e) {
            throw new ProcException(e, this);
        }
    }

    private String expand(String sql, ProcContext ctx) {
        Iterator iter = ctx.attributeIterator();

        while (iter.hasNext()) {
            Map.Entry attr = (Map.Entry) iter.next();
            String pattern = "${" + (String) attr.getKey() + "}";
            Object value = attr.getValue();
            if (value instanceof String) {
                sql = StringUtils.replace(sql, pattern, (String) value);
            }
        }
        return sql;
    }

    /**
     * Bind named attributes from a {@link ProcContext} to bind variables
     * in a <code>PreparedStatement</code>. We rely on both
     * {@link ProcContext#attributeIterator()} and the bind variables array
     * being sorted into alphabetic order, then perform a simple merge
     * between the two lists to do the job in one pass.
     *
     * @param stmt A prepared statement to which we need to bind attribute
     *             values.
     * @param ctx The context which we should take attribute values from.
     *
     * @throws ProcException If a required bind variable could not be found
     *                       in the context, or if there was some JDBC error
     *                       binding an attribute to the corresponding variable.
     */
    private void bindParameters(PreparedStatement stmt, ProcContext ctx)
        throws ProcException
    {
        BindVariable[] bindVariables = getBindVariables();
        int i;
        Iterator iter = ctx.attributeIterator();
        Map.Entry attr = null;

        for (i = 0; i < bindVariables.length; i++) {
            BindVariable bind = bindVariables[i];

            // find attribute matching bind variable
            while (true) {
                int compare = 0;

                if (attr != null)
                    compare = bind.name.compareTo((String) attr.getKey());
                if (attr == null || compare > 0) {
                    if (iter.hasNext()) {
                        attr = (Map.Entry) iter.next();
                    } else {
                        throw variableNotFound(bind.name);
                    }
                } else if (compare < 0) {
                    throw variableNotFound(bind.name);
                } else { // compare == 0
                    break;
                }
            }

            try {
                stmt.setObject(i + 1, attr.getValue());
            } catch (SQLException e) {
                throw new ProcException("setting bind variable " + bind.name);
            }
        }
    }

    private ProcException variableNotFound(String name) {
        return new ProcException("bind variable " + name + " not found", this);
    }

    private static final class BindVariable {
        public static final BindVariable[] EMPTY_ARRAY = new BindVariable[0];

        public final String name;

        public BindVariable(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    private static final class BindVariableComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            BindVariable l = (BindVariable) o1;
            BindVariable r = (BindVariable) o2;
            return l.name.compareTo(r.name);
        }
    }
}
