/*
 * $Id: AbstractConnectionManager.java,v 1.3 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.codehaus.plexus.logging.AbstractLogEnabled;

/**
 * Abstract base class to be used when implementing the
 * {@link ConnectionManager} interface. Provides:
 * <ul>
 * <li>An implementation of the {@link ConnectionManager#getDialect()}
 * method that derives the dialect from the package name of the JDBC
 * driver class.
 * </ul>
 *
 * <p>
 * Dialects recognised by this implementation are currently:
 *
 * <table>
 * <tr>
 * <th>dialect</th>
 * <th>meaning</th>
 * </tr>
 * <tr>
 * <td>unset</td>
 * <td>Dialect has not been set; the {@link ConnectionManager} implementation
 * has not initialised correctly.</td>
 * </tr>
 * <tr>
 * <td>unknown</td>
 * <td>JDBC driver class is unrecognised</td>
 * </tr>
 * </table>
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public abstract class AbstractConnectionManager extends AbstractLogEnabled {
    private String dialect = "unset";

    public String getDialect() {
        return dialect;
    }

    private static Properties loadProperties() throws IOException {
        Class c;
        InputStream stream;
        Properties p;

        c = AbstractConnectionManager.class;
        stream = c.getResourceAsStream("drivers.properties");
        p = new Properties();
        p.load(stream);
        stream.close();
        return p;
    }

    protected void computeDialect(String driverName) throws IOException {
        Properties properties;
        Enumeration prefixes;
        String foundDialect = null;

        properties = loadProperties();
        prefixes = properties.propertyNames();
        while (prefixes.hasMoreElements()) {
            String prefix = (String) prefixes.nextElement();
            if (driverName.startsWith(prefix))
                foundDialect = properties.getProperty(prefix);
        }
        if (foundDialect == null)
            dialect = "unknown";
        else
            dialect = foundDialect;
    }
}
