/*
 * $Id: CatalogTestGeneric.java,v 1.1 2004-01-23 19:48:02 mhw Exp $
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
 * @version $Revision: 1.1 $
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
}
