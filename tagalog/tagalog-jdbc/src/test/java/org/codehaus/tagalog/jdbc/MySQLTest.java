/*
 * $Id: MySQLTest.java,v 1.3 2004-09-22 15:54:18 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.net.URL;

import org.codehaus.plexus.PlexusTestCase;


/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class MySQLTest extends PlexusTestCase {
    private static final String CATALOG_NAME = "MySQLCatalog.xml";

    private Catalog catalog;

    public void setUp() throws Exception {
        super.setUp();
        URL url = MySQLTest.class.getResource(CATALOG_NAME);
        catalog = new Catalog(getContainer(), url);
    }

    public void testDummy() {
        // placeholder as there are no tests here at the moment.
    }
}
