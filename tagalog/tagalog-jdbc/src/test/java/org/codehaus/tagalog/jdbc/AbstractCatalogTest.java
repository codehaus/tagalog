/*
 * $Id: AbstractCatalogTest.java,v 1.5 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import org.codehaus.plexus.PlexusTestCase;

/**
 * Abstract base class for the catalog tests.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
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
