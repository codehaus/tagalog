/*
 * $Id: SQLStatement.java,v 1.10 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.10 $
 */
public class SQLStatement extends AbstractSQLStatement {
    protected ResultSetWrapper createResultSetWrapper(PreparedStatement stmt,
                                                      ProcContext ctx)
        throws SQLException, ProcException
    {
        if (generatesKeys())
            return ResultSetWrapper.fromGeneratedKeys(this, ctx, stmt);
        else
            throw new ProcException("statement created a result set", this);
    }
}
