/*
 * $Id: SQLStatement.java,v 1.9 2004-02-26 12:28:31 mhw Exp $
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
