/*
 * $Id: OracleCatalogTest.java,v 1.12 2004-10-05 17:00:15 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.12 $
 */
public final class OracleCatalogTest extends AbstractCatalogTest {
    private static final String PLEXUS_CONF = "OracleSQLTest.xml";

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }

    public void testDummy() {
        // forces Eclipse to recognise the test
    }
}
