/*
 * $Id: OracleCatalogTest.java,v 1.11 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.11 $
 */
public final class OracleCatalogTest extends AbstractCatalogTest {
    private static final String PLEXUS_CONF = "OracleSQLTest.xml";

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }
}
