/*
 * $Id: UpdateStatement.java,v 1.1 2004-10-06 10:49:09 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class UpdateStatement extends SQLStatement {
    public UpdateStatement() {
        setReturnsUpdateCount(true);
    }

    public Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException
    {
        PreparedStatement stmt = prepareAndExecute(catalog, proc, ctx);

        if (stmt != null) {
            if (!generatesKeys()) {
                try {
                    return new Integer(stmt.getUpdateCount());
                } catch (SQLException e) {
                    throw new ProcException(e, this);
                } finally {
                    try {
                        Connection connection = stmt.getConnection();
                        stmt.close();
                        ctx.returnConnection(connection);
                    } catch (SQLException e2) {
                        // ignore
                    }
                }
            } else
                return wrapResultSet(stmt, ctx);
        }
        return null;
    }
}
