/*
 * $Id: OracleConnectionTest.java,v 1.1 2004-12-16 18:32:00 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.codehaus.plexus.PlexusTestCase;

/**
 * Test Oracle connection.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class OracleConnectionTest extends PlexusTestCase {
    private static final String PLEXUS_CONF = "OracleSQLTest.xml";

    public InputStream getCustomConfiguration() throws Exception {
        return getResourceAsStream(PLEXUS_CONF);
    }

    public void testGetConnection() throws Exception {
        ConnectionManager mgr;
        Connection con;
        DatabaseMetaData meta;

        mgr = (ConnectionManager) lookup(ConnectionManager.ROLE);
        assertEquals("oracle", mgr.getDialect());
        con = mgr.getConnection();
        assertNotNull(con);
        meta = con.getMetaData();
        assertNotNull(meta);
    }
}
