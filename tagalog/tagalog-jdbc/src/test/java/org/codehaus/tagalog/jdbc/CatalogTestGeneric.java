/*
 * $Id: CatalogTestGeneric.java,v 1.2 2004-01-28 13:02:26 mhw Exp $
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

import junit.framework.TestCase;

import org.codehaus.plexus.PlexusContainer;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
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
     * Test a simple query that needs no table to have been created.
     */
    public void testQueryNoTable() throws Exception {
        rs = catalog.query("query-no-table");
        assertEquals(42, rs.getInt("result"));
        assertEquals(42, rs.getInt(1));
        rs.close();
    }

    /**
     * Create a test table and load some data into it.
     */
    public void testTableQueries() throws Exception {
        try {
            catalog.run("ttq-drop-table");
        } catch (ProcException e) {
            // ignore
        }

        catalog.run("ttq-create-table");
        catalog.run("ttq-create-data");

        rs = catalog.query("ttq-q-no-rows");
        assertNull(rs);

        try {
            rs = catalog.query("ttq-q-no-rows-get-one");
            fail("query for no rows should throw exception");
        } catch (ProcException e) {
            assertTrue(e instanceof TooManyRowsException);
        }

        try {
            rs = catalog.query("ttq-q-no-rows-get-lots");
            fail("query for no rows should throw exception");
        } catch (ProcException e) {
            assertTrue(e instanceof TooManyRowsException);
        }

        catalog.run("ttq-drop-table");
    }
}
