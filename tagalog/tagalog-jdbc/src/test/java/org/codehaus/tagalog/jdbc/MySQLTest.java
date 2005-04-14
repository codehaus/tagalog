/*
 * $Id: MySQLTest.java,v 1.5 2005-04-14 09:24:48 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.net.URL;

import org.codehaus.plexus.PlexusTestCase;


/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
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
        assertNotNull(catalog);
    }
}
