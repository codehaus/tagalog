/*
 * $Id: AbstractCatalogTest.java,v 1.4 2004-09-24 16:19:10 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import org.codehaus.plexus.PlexusTestCase;

/**
 * Abstract base class for the catalog tests.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public abstract class AbstractCatalogTest extends PlexusTestCase {

    protected CatalogTestGeneric genericTest;

    public void setUp() throws Exception {
        super.setUp();
        genericTest = new CatalogTestGeneric(getContainer());
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

    public void testDataTypeCoverage() throws Exception {
        genericTest.testDataTypeCoverage();
    }

    public void testInsertKeyGeneration() throws Exception {
        genericTest.testInsertKeyGeneration();
    }

}
