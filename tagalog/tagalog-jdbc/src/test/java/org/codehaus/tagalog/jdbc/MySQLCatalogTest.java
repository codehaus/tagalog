/*
 * $Id: MySQLCatalogTest.java,v 1.5 2004-02-25 16:28:45 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
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
 * @version $Revision: 1.5 $
 */
public final class MySQLCatalogTest extends PlexusTestCase {
    private static final String PLEXUS_CONF = "MySQLTest.xml";

    private CatalogTestGeneric genericTest;

    public MySQLCatalogTest(String testName) {
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

    public void testTableQueries() throws Exception {
        genericTest.testTableQueries();
    }

    public void testTableQueriesWithBindVariables() throws Exception {
        genericTest.testTableQueriesWithBindVariables();
    }

    public void testInsertWithBindVariables() throws Exception {
        genericTest.testInsertWithBindVariables();
    }

}
