/*
 * $Id: QueryStatement.java,v 1.9 2004-02-26 12:28:31 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.9 $
 */
public final class QueryStatement extends AbstractSQLStatement {
    public QueryStatement() {
        super.setQueryType(QueryType.ZERO_OR_MORE);
    }

    public void setQueryType(QueryType queryType) {
        super.setQueryType(queryType);
    }

    protected ResultSetWrapper createResultSetWrapper(PreparedStatement stmt,
                                                      ProcContext ctx)
        throws SQLException, ProcException
    {
        return ResultSetWrapper.fromResults(this, ctx, stmt);
    }
}
