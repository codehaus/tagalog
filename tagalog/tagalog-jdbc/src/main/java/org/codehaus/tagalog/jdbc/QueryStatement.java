/*
 * $Id: QueryStatement.java,v 1.10 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.10 $
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
