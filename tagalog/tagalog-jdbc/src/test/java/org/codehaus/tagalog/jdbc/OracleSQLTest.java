/*
 * $Id: OracleSQLTest.java,v 1.1 2004-01-23 15:22:40 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
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
 * @version $Revision: 1.1 $
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

    public void testSequences() throws ProcException {
        ProcContext ctx = new ProcContext();
        Proc proc;

        ctx.set("sequenceName", "cust_seq");
        catalog.execute("create-sequence", ctx);
        catalog.execute("next-sequence-val", ctx);
        catalog.execute("drop-sequence", ctx);
    }
}
