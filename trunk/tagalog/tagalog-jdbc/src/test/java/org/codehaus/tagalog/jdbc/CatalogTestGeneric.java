/*
 * $Id: CatalogTestGeneric.java,v 1.5 2004-01-28 16:34:11 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.TestCase;

import org.codehaus.plexus.PlexusContainer;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public final class CatalogTestGeneric extends TestCase {
    private static final String CATALOG_NAME = "CatalogTestGenericCatalog.xml";

    public CatalogTestGeneric(PlexusContainer container)
        throws Exception
    {
        URL url = OracleCatalogTest.class.getResource(CATALOG_NAME);
        catalog = new Catalog(container, url);
    }

    private Catalog catalog;

    private ResultSet rs;

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
}
