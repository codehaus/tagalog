/*
 * $Id: SQLStatement.java,v 1.11 2005-04-14 13:51:32 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.11 $
 */
public class SQLStatement extends AbstractSQLStatement {
    protected ResultSetWrapper createResultSetWrapper(PreparedStatement stmt,
                                                      ProcContext ctx)
        throws SQLException, ProcException
    {
        if (generatesKeys())
            return ResultSetWrapper.fromGeneratedKeys(this, ctx, stmt);
        throw new ProcException("statement created a result set", this);
    }
}
