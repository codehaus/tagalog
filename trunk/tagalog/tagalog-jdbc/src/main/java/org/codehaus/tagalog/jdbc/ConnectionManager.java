/*
 * $Id: ConnectionManager.java,v 1.2 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Component that manages database connections.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public interface ConnectionManager {
    /**
     * Interface's role.
     */
    String ROLE = ConnectionManager.class.getName();

    /**
     * <p>Attempts to establish a connection with the data source that
     * this <code>DataSource</code> object represents.
     *
     * @return  a connection to the data source
     * @exception SQLException if a database access error occurs
     */
    Connection getConnection() throws SQLException;

    /**
     * Returns the SQL dialect that is required by the connection.
     * Consult the documentation for the component implementation to find out
     * the values that this method will return.
     *
     * @return String representing the SQL dialect.
     */
    String getDialect();
}
