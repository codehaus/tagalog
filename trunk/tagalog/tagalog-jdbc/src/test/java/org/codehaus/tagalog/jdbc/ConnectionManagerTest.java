/*
 * $Id: ConnectionManagerTest.java,v 1.2 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.codehaus.plexus.PlexusTestCase;

/**
 * Component that manages database connections.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public class ConnectionManagerTest extends PlexusTestCase {

    public ConnectionManagerTest(String testName) {
        super(testName);
    }

    public void testGetConnection() throws Exception {
        ConnectionManager mgr;
        Connection con;
        DatabaseMetaData meta;

        mgr = (ConnectionManager) lookup(ConnectionManager.ROLE);
        assertEquals("mysql", mgr.getDialect());
        con = mgr.getConnection();
        assertNotNull(con);
        meta = con.getMetaData();
        assertNotNull(meta);

        mgr = (ConnectionManager) lookup(ConnectionManager.ROLE, "oracle");
        assertEquals("oracle", mgr.getDialect());
        con = mgr.getConnection();
        assertNotNull(con);
        meta = con.getMetaData();
        assertNotNull(meta);
    }
}
