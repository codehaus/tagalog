/*
 * $Id: QueryStatement.java,v 1.3 2004-01-28 11:29:25 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
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
 * @version $Revision: 1.3 $
 */
public final class QueryStatement extends SQLStatement {
    public void setQueryType(QueryType queryType) {
        super.setQueryType(queryType);
    }

    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        PreparedStatement stmt = super.prepareAndExecute(catalog, proc, ctx);

        if (stmt == null) {
            throw new ProcException("query created no result set", this);
        }
        try {
            return new ResultSetWrapper(getQueryType(), ctx, stmt);
        } catch (SQLException e) {
            throw new ProcException(e, this);
        }
    }
}
