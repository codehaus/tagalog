/*
 * $Id: HSQLDBCatalogTest.java,v 1.1 2004-12-17 13:45:17 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class HSQLDBCatalogTest extends AbstractCatalogTest {
    private static final String PLEXUS_CONF = "HSQLDBTest.xml";

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }

    public void testDummy() {
        // forces Eclipse to recognise the test
    }
}
