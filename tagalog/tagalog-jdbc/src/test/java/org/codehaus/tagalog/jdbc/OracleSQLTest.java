/*
 * $Id: OracleSQLTest.java,v 1.3 2004-01-23 18:49:24 mhw Exp $
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

import org.codehaus.plexus.PlexusTestCase;


/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class OracleSQLTest extends PlexusTestCase {
    private static final String CATALOG_NAME = "OracleSQLCatalog.xml";

    private Catalog catalog;

    public OracleSQLTest(String testName) {
        super(testName);
    }

    public void setUp() throws Exception {
        super.setUp();
        URL url = OracleSQLTest.class.getResource(CATALOG_NAME);
        catalog = new Catalog(getContainer(), url);
    }

    public void testSequences() throws Exception {
        ProcContext ctx = new ProcContext();
        ResultSet rs;

        ctx.setAttribute("sequenceName", "cust_seq");
        catalog.run("create-sequence", ctx);
        rs = catalog.query("next-sequence-val", ctx);
        assertEquals(1, rs.getInt(1));
        rs.close();
        catalog.run("drop-sequence", ctx);
    }
}
