/*
 * $Id: CatalogTestGeneric.java,v 1.13 2004-08-16 15:58:05 mhw Exp $
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
import java.sql.SQLException;
import java.sql.Types;

import junit.framework.Assert;

import org.codehaus.plexus.PlexusContainer;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.13 $
 */
public final class CatalogTestGeneric extends Assert {
    private static final String CATALOG_NAME = "CatalogTestGenericCatalog.xml";

    public CatalogTestGeneric(PlexusContainer container)
        throws Exception
    {
        URL url = OracleCatalogTest.class.getResource(CATALOG_NAME);
        catalog = new Catalog(container, url);
    }

    private Catalog catalog;

    private ResultSet rs;

    private ProcContext ctx;

    public CatalogTestGeneric(Catalog catalog) {
        this.catalog = catalog;
    }

    /**
     * Test that a <code>SQLException</code> contains the message text
     * produced by a {@link TooFewRowsException}. This is necessary because
     * <code>SQLException</code> only supports exception chaining with
     * other <code>SQLException</code>s.
     *
     * @param e An <code>SQLException</code> that might be created from
     * a <code>TooFewRowsException</code>.
     */
    private void assertTooFewRows(SQLException e) {
        String expected = TooFewRowsException.MESSAGE_PREFIX;

        assertTrue(e.getMessage().indexOf(expected) == 0);
    }

    /**
     * Test that a <code>SQLException</code> contains the message text
     * produced by a {@link TooManyRowsException}. This is necessary because
     * <code>SQLException</code> only supports exception chaining with
     * other <code>SQLException</code>s.
     *
     * @param e An <code>SQLException</code> that might be created from
     * a <code>TooManyRowsException</code>.
     */
    private void assertTooManyRows(SQLException e) {
        String expected = TooManyRowsException.MESSAGE_PREFIX;

        assertTrue(e.getMessage().indexOf(expected) == 0);
    }

    /**
     * Test a simple query that needs no table to have been created.
     */
    public void testQueryNoTable() throws Exception {
        rs = catalog.query("query-no-table");
        assertEquals(42, rs.getInt("result"));
        assertEquals(42, rs.getInt(1));
        rs.close();
    }

    /**
     * Create a test table and load some data into it, then try each of the
     * query types (rows="zero", rows="zero-or-one", etc).
     */
    public void testTableQueries() throws Exception {
        try {
            catalog.run("ttq-drop-table");
        } catch (ProcException e) {
            // ignore
        }

        catalog.run("ttq-create-table");
        catalog.run("ttq-create-data");

        // queries for zero rows

        rs = catalog.query("ttq-q-no-rows-get-zero");
        assertNull(rs);

        try {
            rs = catalog.query("ttq-q-no-rows-get-one");
            fail("query should throw exception");
        } catch (ProcException e) {
            assertTrue(e instanceof TooManyRowsException);
        }

        try {
            rs = catalog.query("ttq-q-no-rows-get-many");
            fail("query should throw exception");
        } catch (ProcException e) {
            assertTrue(e instanceof TooManyRowsException);
        }

        // queries for zero or one row

        rs = catalog.query("ttq-q-zero-or-one-rows-get-zero");
        assertNull(rs);

        rs = catalog.query("ttq-q-zero-or-one-rows-get-one");
        assertNotNull(rs);
        if (rs != null) {
            assertEquals(1, rs.getInt(1));
            assertEquals("mhw", rs.getString(2));
            rs.close();
        }

        try {
            rs = catalog.query("ttq-q-zero-or-one-rows-get-many");
            assertEquals(1, rs.getInt(1));
            rs.next();
            fail("query should throw exception");
        } catch (SQLException e) {
            assertTooManyRows(e);
        } finally {
            rs.close();
        }

        try {
            rs = catalog.query("ttq-q-zero-or-one-rows-get-many");
            assertNotNull(rs);
            if (rs != null) {
                assertEquals(1, rs.getInt(1));
                assertEquals("mhw", rs.getString(2));
                rs.close();
            }
            fail("query should throw exception");
        } catch (SQLException e) {
            assertTooManyRows(e);
        }

        // queries for one row

        try {
            rs = catalog.query("ttq-q-one-row-get-zero");
            fail("query should throw exception");
        } catch (ProcException e) {
            assertTrue(e instanceof TooFewRowsException);
        }

        rs = catalog.query("ttq-q-one-row-get-one");
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        rs = catalog.query("ttq-q-one-row-get-many");
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        try {
            rs.close();
            fail("close should throw exception");
        } catch (SQLException e) {
            assertTooManyRows(e);
        }

        rs = catalog.query("ttq-q-one-row-get-many");
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        try {
            rs.next();
            fail("next should throw exception");
        } catch (SQLException e) {
            assertTooManyRows(e);
        }
        rs.close();

        // queries for one or more rows

        try {
            rs = catalog.query("ttq-q-one-or-more-rows-get-zero");
            fail("query should throw exception");
        } catch (ProcException e) {
            assertTrue(e instanceof TooFewRowsException);
        }

        rs = catalog.query("ttq-q-one-or-more-rows-get-one");
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        rs = catalog.query("ttq-q-one-or-more-rows-get-many");
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertTrue(rs.next());
        assertEquals(2, rs.getInt(1));
        assertEquals("fred", rs.getString(2));
        assertTrue(rs.next());
        assertEquals(42, rs.getInt(1));
        assertEquals("answer", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        // queries for zero or more rows

        rs = catalog.query("ttq-q-zero-or-more-rows-get-zero");
        assertNull(rs);

        rs = catalog.query("ttq-q-zero-or-more-rows-get-one");
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        rs = catalog.query("ttq-q-zero-or-more-rows-get-many");
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertTrue(rs.next());
        assertEquals(2, rs.getInt(1));
        assertEquals("fred", rs.getString(2));
        assertTrue(rs.next());
        assertEquals(42, rs.getInt(1));
        assertEquals("answer", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        catalog.run("ttq-drop-table");
    }

    /**
     * Create a test table and load some data into it, then query
     * using bind variables.
     */
    public void testTableQueriesWithBindVariables() throws Exception {
        try {
            catalog.run("ttq-drop-table");
        } catch (ProcException e) {
            // ignore
        }

        catalog.run("ttq-create-table");
        catalog.run("ttq-create-data");

        ctx = new ProcContext();
        ctx.setInt("id", 1);
        rs = catalog.query("ttq-q-by-id", ctx);
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        ctx = new ProcContext();
        ctx.setString("name", "fred");
        rs = catalog.query("ttq-q-by-name", ctx);
        assertEquals(2, rs.getInt(1));
        assertEquals("fred", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        ctx = new ProcContext();
        ctx.setString("table", "catalog_test");
        ctx.setString("column", "id");
        ctx.setInt("value", 1);
        rs = catalog.query("ttq-q-generic", ctx);
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        ctx = new ProcContext();
        ctx.setString("table", "catalog_test");
        ctx.setString("column", "name");
        ctx.setString("value", "mhw");
        rs = catalog.query("ttq-q-generic", ctx);
        assertEquals(1, rs.getInt(1));
        assertEquals("mhw", rs.getString(2));
        assertFalse(rs.next());
        rs.close();

        catalog.run("ttq-drop-table");
    }

    /**
     * Test use of bind variables by inserting data into a table.
     */
    public void testInsertWithBindVariables() throws Exception {
        try {
            catalog.run("tbind-drop-table");
        } catch (ProcException e) {
            // ignore
        }

        catalog.run("tbind-create-table");

        ctx = new ProcContext();
        ctx.setInt("id", 1);
        ctx.setString("zippy", "zippy");
        ctx.setString("bungle", "bungle");
        ctx.setString("rod", "rod");
        ctx.setString("freddy", "freddy");
        ctx.setString("jane", "jane");
        catalog.execute("tbind-insert", ctx);

        ctx = new ProcContext();
        ctx.setInt("id", 1);
        rs = catalog.query("tbind-query", ctx);
        assertEquals(1, rs.getInt("id"));
        assertEquals("rod", rs.getString("a"));
        assertEquals("jane", rs.getString("b"));
        assertEquals("freddy", rs.getString("c"));
        assertEquals("zippy", rs.getString("d"));
        assertEquals("bungle", rs.getString("e"));
        assertFalse(rs.next());
        rs.close();

        catalog.run("tbind-drop-table");
    }

    /**
     * Test the common idiom of creating a table row with a unique
     * identifier allocated as part of the SQL statement.
     */
    public void testInsertKeyGeneration() throws Exception {
        try {
            catalog.run("tikg-d-table");
        } catch (ProcException e) {
            // ignore
        }

        catalog.run("tikg-c-table");

        ctx = new ProcContext();
        ctx.setString("value", "mhw");
        ctx.setInt("length", 3);
        rs = (ResultSet) catalog.execute("tikg-insert", ctx);
        assertEquals(1, rs.getInt(1));
        rs.close();

        ctx = new ProcContext();
        ctx.setString("value", "bob");
        ctx.setNull("length", Types.INTEGER);
        rs = (ResultSet) catalog.execute("tikg-insert", ctx);
        assertEquals(2, rs.getInt(1));
        rs.close();

        ctx = new ProcContext();
        ctx.setInt("id", 1);
        rs = (ResultSet) catalog.execute("tikg-query", ctx);
        assertEquals(1, rs.getInt("id"));
        assertEquals("mhw", rs.getString("value"));
        assertEquals(3, rs.getInt("length"));
        rs.close();

        ctx = new ProcContext();
        ctx.setInt("id", 2);
        rs = (ResultSet) catalog.execute("tikg-query", ctx);
        assertEquals(2, rs.getInt("id"));
        assertEquals("bob", rs.getString("value"));
        assertEquals(0, rs.getInt("length"));
        assertTrue(rs.wasNull());
        rs.close();

        catalog.run("tikg-d-table");
    }
}
