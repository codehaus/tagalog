/*
 * $Id: OracleCatalogTest.java,v 1.1 2004-01-23 19:48:02 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.io.InputStream;

import org.codehaus.plexus.PlexusTestCase;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class OracleCatalogTest extends PlexusTestCase {
    private static final String PLEXUS_CONF = "OracleSQLTest.xml";

    private static final String CATALOG_NAME = "OracleSQLCatalog.xml";

    private CatalogTestGeneric genericTest;

    public OracleCatalogTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        genericTest = new CatalogTestGeneric(getContainer());
    }

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }

    public void testQueryNoTable() throws Exception {
        genericTest.testQueryNoTable();
    }

}
