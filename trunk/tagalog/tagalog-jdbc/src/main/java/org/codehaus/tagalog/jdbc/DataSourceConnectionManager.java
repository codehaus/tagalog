/*
 * $Id: DataSourceConnectionManager.java,v 1.6 2005-03-01 15:49:42 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;

import org.codehaus.tagalog.conv.PropertySetter;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
 */
public class DataSourceConnectionManager
    extends AbstractConnectionManager
    implements ConnectionManager, Initializable
{
    private String dataSourceClass;

    private Properties properties;

    private DataSource dataSource;

    public void initialize() throws Exception {
        Class dsClass = Class.forName(dataSourceClass);
        PropertySetter p = new PropertySetter(dsClass);

        dataSource = (DataSource) dsClass.newInstance();
        computeDialect(dataSourceClass);

        for (Iterator iter = properties.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Entry) iter.next();
            String name = (String) entry.getKey();
            String value = (String) entry.getValue();
            p.setProperty(dataSource, name, value);
        }
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException(
                "connection manager not initialized");
        }
        return dataSource.getConnection();
    }
}
