/*
 * $Id: MySQLTest.java,v 1.1 2004-02-25 18:04:31 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Types;

import org.codehaus.plexus.PlexusTestCase;


/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class MySQLTest extends PlexusTestCase {
    private static final String CATALOG_NAME = "MySQLCatalog.xml";

    private Catalog catalog;

    public MySQLTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        URL url = MySQLTest.class.getResource(CATALOG_NAME);
        catalog = new Catalog(getContainer(), url);
    }

    public void testAutoIncrement() throws Exception {
        ProcContext ctx;
        ResultSet rs;

        try {
            catalog.run("autoinc-d-table");
        } catch (ProcException e) {
            // ignore
        }

        catalog.run("autoinc-c-table");

        ctx = new ProcContext();
        ctx.setString("value", "mhw");
        ctx.setInt("length", 3);
        rs = (ResultSet) catalog.execute("autoinc-insert", ctx);
        assertEquals(1, rs.getInt(1));
        rs.close();

        ctx = new ProcContext();
        ctx.setString("value", "bob");
        ctx.setNull("length", Types.INTEGER);
        rs = (ResultSet) catalog.execute("autoinc-insert", ctx);
        assertEquals(2, rs.getInt(1));
        rs.close();

        ctx = new ProcContext();
        ctx.setInt("id", 1);
        rs = (ResultSet) catalog.execute("autoinc-query", ctx);
        assertEquals(1, rs.getInt("id"));
        assertEquals("mhw", rs.getString("value"));
        assertEquals(3, rs.getInt("length"));
        rs.close();

        ctx = new ProcContext();
        ctx.setInt("id", 2);
        rs = (ResultSet) catalog.execute("autoinc-query", ctx);
        assertEquals(2, rs.getInt("id"));
        assertEquals("bob", rs.getString("value"));
        assertEquals(0, rs.getInt("length"));
        assertTrue(rs.wasNull());
        rs.close();

        catalog.run("autoinc-d-table");
    }
}
