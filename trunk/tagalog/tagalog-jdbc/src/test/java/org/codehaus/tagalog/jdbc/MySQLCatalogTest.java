/*
 * $Id: MySQLCatalogTest.java,v 1.10 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.10 $
 */
public final class MySQLCatalogTest extends AbstractCatalogTest {
    private static final String PLEXUS_CONF = "MySQLTest.xml";

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }
}
