/*
 * $Id: ProcContext.java,v 1.9 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Ref;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

/**
 * The context within which a procedure will be executed.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.9 $
 */
public final class ProcContext {

    //
    // Connection management.
    //

    private String connectionName;

    /**
     * Set the name of the connection that statements running in this context
     * will use.
     *
     * @param connectionName
     */
    void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    private ConnectionManager connectionManager;

    synchronized ConnectionManager getConnectionManager(Catalog catalog)
        throws ComponentLookupException
    {
        PlexusContainer container;
        Object o;

        if (connectionManager == null) {
            container = catalog.getContainer();
            if (connectionName == null)
                o = container.lookup(ConnectionManager.ROLE);
            else
                o = container.lookup(ConnectionManager.ROLE, connectionName);
            connectionManager = (ConnectionManager) o;
        }
        return connectionManager;
    }

    String getDialect(Catalog catalog) throws ComponentLookupException {
        return getConnectionManager(catalog).getDialect();
    }

    private Connection connection;

    private int references;

    void begin() {
        if (connection != null) {
            throw new IllegalStateException(
                "begin called when connection already held");
        }

        // We consider the caller of the begin/end pair to hold a reference
        // to the connection so that we don't close the connection between
        // each statement.
        references = 1;
    }

    synchronized Connection getConnection(Catalog catalog)
        throws SQLException, ComponentLookupException
    {
         if (connection == null) {
             if (references != 1) {
                 throw new IllegalStateException("reference count ("
                    + references + ") wrong in getConnection");
             }
             connection = getConnectionManager(catalog).getConnection();
         }
         references++;
         return connection;
    }

    void returnConnection(Connection returnedConnection)
        throws SQLException
    {
        if (returnedConnection != connection)
            throw new IllegalArgumentException("returned connection "
                                               + returnedConnection
                                               + " not owned by this context");
        references--;
        if (references == 0) {
            try {
                connection.close();
            } finally {
                connection = null;
            }
        }
    }

    void end() throws ProcException {
        references--;
        if (references == 0 && connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ProcException("close failed", e);
            } finally {
                connection = null;
            }
        }
    }

    //
    // Attribute management
    //

    private SortedMap attributes = new java.util.TreeMap();

    Iterator attributeIterator() {
        Set entries = Collections.unmodifiableSortedMap(attributes).entrySet();
        return entries.iterator();
    }

    private void addAttribute(String name, Attribute value) {
        if (name == null)
            throw new NullPointerException("name is null");
        attributes.put(name, value);
    }

    //
    // Substitutes for equivalent methods on {@link PreparedStatement}.
    //
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setArray(int, java.sql.Array)
     */
    public void setArray(String name, Array x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream, int)
     */
    public void setAsciiStream(String name, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setBigDecimal(int, java.math.BigDecimal)
     */
    public void setBigDecimal(String name, BigDecimal x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream, int)
     */
    public void setBinaryStream(String name, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setBlob(int, java.sql.Blob)
     */
    public void setBlob(String name, Blob x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setBoolean(int, boolean)
     */
    public void setBoolean(String name, boolean x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setByte(int, byte)
     */
    public void setByte(String name, byte x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setBytes(int, byte[])
     */
    public void setBytes(String name, byte[] x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader, int)
     */
    public void setCharacterStream(String name, Reader reader, int length) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setClob(int, java.sql.Clob)
     */
    public void setClob(String name, Clob x) {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setDate(int, java.sql.Date, java.util.Calendar)
     */
    public void setDate(String name, Date value, Calendar calendar) {
        Attribute.DateCalendarPair p;

        p = new Attribute.DateCalendarPair(value, calendar);
        addAttribute(name, new Attribute(p, Attribute.DATE));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setDate(int, java.sql.Date)
     */
    public void setDate(String name, Date value) {
        Attribute.DateCalendarPair p;

        p = new Attribute.DateCalendarPair(value, null);
        addAttribute(name, new Attribute(p, Attribute.DATE));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setDouble(int, double)
     */
    public void setDouble(String name, double x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setFloat(int, float)
     */
    public void setFloat(String name, float x) {
        throw new UnsupportedOperationException();
    }
    
    public void setInt(String name, int value) {
        addAttribute(name, new Attribute(new Integer(value), Attribute.INT));
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setLong(int, long)
     */
    public void setLong(String name, long x) {
        throw new UnsupportedOperationException();
    }
    
    public void setNull(String name, int sqlType, String typeName) {
        addAttribute(name, new Attribute(Attribute.NULL, sqlType, typeName));
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setNull(int, int)
     */
    public void setNull(String name, int sqlType) {
        addAttribute(name, new Attribute(Attribute.NULL, sqlType));
    }


    public void setObject(String name, Object value) {
        addAttribute(name, new Attribute(value, Attribute.OBJECT));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int, int)
     */
    public void setObject(String name, Object x, int targetSqlType, int scale) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int)
     */
    public void setObject(String name, Object x, int targetSqlType) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setRef(int, java.sql.Ref)
     */
    public void setRef(String name, Ref x) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setShort(int, short)
     */
    public void setShort(String name, short x) {
        throw new UnsupportedOperationException();
    }
    
    public void setString(String name, String value) {
        addAttribute(name, new Attribute(value, Attribute.STRING));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setTime(int, java.sql.Time, java.util.Calendar)
     */
    public void setTime(String name, Time value, Calendar calendar) {
        Attribute.DateCalendarPair p;

        p = new Attribute.DateCalendarPair(value, calendar);
        addAttribute(name, new Attribute(p, Attribute.TIME));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setTime(int, java.sql.Time)
     */
    public void setTime(String name, Time value) {
        Attribute.DateCalendarPair p;

        p = new Attribute.DateCalendarPair(value, null);
        addAttribute(name, new Attribute(p, Attribute.TIME));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp, java.util.Calendar)
     */
    public void setTimestamp(String name, Timestamp value, Calendar calendar) {
        Attribute.DateCalendarPair p;

        p = new Attribute.DateCalendarPair(value, calendar);
        addAttribute(name, new Attribute(p, Attribute.TIMESTAMP));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp)
     */
    public void setTimestamp(String name, Timestamp value) {
        Attribute.DateCalendarPair p;

        p = new Attribute.DateCalendarPair(value, null);
        addAttribute(name, new Attribute(p, Attribute.TIMESTAMP));
    }

    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setUnicodeStream(int, java.io.InputStream, int)
     */
    public void setUnicodeStream(String name, InputStream x, int length) {
        throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see java.sql.PreparedStatement#setURL(int, java.net.URL)
     */
    public void setURL(String name, URL x) {
        throw new UnsupportedOperationException();
    }

    //
    // Additional convenience methods.
    //

    public void setDate(String name, java.util.Date value, Calendar calendar) {
        setDate(name, new Date(value.getTime()), calendar);
    }

    public void setDate(String name, java.util.Date value) {
        setDate(name, new Date(value.getTime()));
    }

    public void setTime(String name, java.util.Date value, Calendar calendar) {
        setTime(name, new Time(value.getTime()), calendar);
    }

    public void setTime(String name, java.util.Date value) {
        setTime(name, new Time(value.getTime()));
    }

    public void setTimestamp(String name, java.util.Date value,
                             Calendar calendar)
    {
        setTimestamp(name, new Timestamp(value.getTime()), calendar);
    }

    public void setTimestamp(String name, java.util.Date value) {
        setTimestamp(name, new Timestamp(value.getTime()));
    }
}
