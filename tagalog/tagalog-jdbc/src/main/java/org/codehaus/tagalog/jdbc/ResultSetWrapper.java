/*
 * $Id: ResultSetWrapper.java,v 1.1 2004-01-23 18:49:24 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class ResultSetWrapper
    implements DiscardableProcResult, ResultSet
{
    private final QueryType queryType;

    private final ProcContext context;
    
    private final Statement statement;

    private final ResultSet wrapped;

    public ResultSetWrapper(QueryType queryType, ProcContext context,
                            Statement statement)
        throws SQLException
    {
        this.queryType = queryType;
        this.context = context;
        this.statement = statement;
        wrapped = statement.getResultSet();
        if (queryType.getMinimumRowCount() > 0) {
            if (!wrapped.next()) {
                throw new SQLException("expected at least one row");
            }
        }
    }

    public void discard() throws ProcException {
        try {
            close();
        } catch (SQLException e) {
            throw new ProcException("failed to discard results", e);
        }
    }

    public void close() throws SQLException {
        Connection connection;

        wrapped.close();
        connection = statement.getConnection();
        statement.close();
        context.returnConnection(connection);
    }

    //
    // Remainder of the ResultSet methods are simple delegating methods.
    //

    /**
     * @param row
     * @return
     * @throws java.sql.SQLException
     */
    public boolean absolute(int row) throws SQLException {
        return wrapped.absolute(row);
    }

    /**
     * @throws java.sql.SQLException
     */
    public void afterLast() throws SQLException {
        wrapped.afterLast();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void beforeFirst() throws SQLException {
        wrapped.beforeFirst();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void cancelRowUpdates() throws SQLException {
        wrapped.cancelRowUpdates();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void clearWarnings() throws SQLException {
        wrapped.clearWarnings();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void deleteRow() throws SQLException {
        wrapped.deleteRow();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return wrapped.equals(obj);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public int findColumn(String columnName) throws SQLException {
        return wrapped.findColumn(columnName);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean first() throws SQLException {
        return wrapped.first();
    }

    /**
     * @param i
     * @return
     * @throws java.sql.SQLException
     */
    public Array getArray(int i) throws SQLException {
        return wrapped.getArray(i);
    }

    /**
     * @param colName
     * @return
     * @throws java.sql.SQLException
     */
    public Array getArray(String colName) throws SQLException {
        return wrapped.getArray(colName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return wrapped.getAsciiStream(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public InputStream getAsciiStream(String columnName) throws SQLException {
        return wrapped.getAsciiStream(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return wrapped.getBigDecimal(columnIndex);
    }

    /**
     * @param columnIndex
     * @param scale
     * @return
     * @throws java.sql.SQLException
     * @deprecated
     */
    public BigDecimal getBigDecimal(int columnIndex, int scale)
        throws SQLException {
        return wrapped.getBigDecimal(columnIndex, scale);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public BigDecimal getBigDecimal(String columnName) throws SQLException {
        return wrapped.getBigDecimal(columnName);
    }

    /**
     * @deprecated
     */
    public BigDecimal getBigDecimal(String columnName, int scale)
        throws SQLException {
        return wrapped.getBigDecimal(columnName, scale);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return wrapped.getBinaryStream(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public InputStream getBinaryStream(String columnName) throws SQLException {
        return wrapped.getBinaryStream(columnName);
    }

    /**
     * @param i
     * @return
     * @throws java.sql.SQLException
     */
    public Blob getBlob(int i) throws SQLException {
        return wrapped.getBlob(i);
    }

    /**
     * @param colName
     * @return
     * @throws java.sql.SQLException
     */
    public Blob getBlob(String colName) throws SQLException {
        return wrapped.getBlob(colName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public boolean getBoolean(int columnIndex) throws SQLException {
        return wrapped.getBoolean(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public boolean getBoolean(String columnName) throws SQLException {
        return wrapped.getBoolean(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public byte getByte(int columnIndex) throws SQLException {
        return wrapped.getByte(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public byte getByte(String columnName) throws SQLException {
        return wrapped.getByte(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public byte[] getBytes(int columnIndex) throws SQLException {
        return wrapped.getBytes(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public byte[] getBytes(String columnName) throws SQLException {
        return wrapped.getBytes(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        return wrapped.getCharacterStream(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public Reader getCharacterStream(String columnName) throws SQLException {
        return wrapped.getCharacterStream(columnName);
    }

    /**
     * @param i
     * @return
     * @throws java.sql.SQLException
     */
    public Clob getClob(int i) throws SQLException {
        return wrapped.getClob(i);
    }

    /**
     * @param colName
     * @return
     * @throws java.sql.SQLException
     */
    public Clob getClob(String colName) throws SQLException {
        return wrapped.getClob(colName);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public int getConcurrency() throws SQLException {
        return wrapped.getConcurrency();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public String getCursorName() throws SQLException {
        return wrapped.getCursorName();
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public Date getDate(int columnIndex) throws SQLException {
        return wrapped.getDate(columnIndex);
    }

    /**
     * @param columnIndex
     * @param cal
     * @return
     * @throws java.sql.SQLException
     */
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return wrapped.getDate(columnIndex, cal);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public Date getDate(String columnName) throws SQLException {
        return wrapped.getDate(columnName);
    }

    /**
     * @param columnName
     * @param cal
     * @return
     * @throws java.sql.SQLException
     */
    public Date getDate(String columnName, Calendar cal) throws SQLException {
        return wrapped.getDate(columnName, cal);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public double getDouble(int columnIndex) throws SQLException {
        return wrapped.getDouble(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public double getDouble(String columnName) throws SQLException {
        return wrapped.getDouble(columnName);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public int getFetchDirection() throws SQLException {
        return wrapped.getFetchDirection();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public int getFetchSize() throws SQLException {
        return wrapped.getFetchSize();
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public float getFloat(int columnIndex) throws SQLException {
        return wrapped.getFloat(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public float getFloat(String columnName) throws SQLException {
        return wrapped.getFloat(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public int getInt(int columnIndex) throws SQLException {
        return wrapped.getInt(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public int getInt(String columnName) throws SQLException {
        return wrapped.getInt(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public long getLong(int columnIndex) throws SQLException {
        return wrapped.getLong(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public long getLong(String columnName) throws SQLException {
        return wrapped.getLong(columnName);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public ResultSetMetaData getMetaData() throws SQLException {
        return wrapped.getMetaData();
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public Object getObject(int columnIndex) throws SQLException {
        return wrapped.getObject(columnIndex);
    }

    /**
     * @param i
     * @param map
     * @return
     * @throws java.sql.SQLException
     */
    public Object getObject(int i, Map map) throws SQLException {
        return wrapped.getObject(i, map);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public Object getObject(String columnName) throws SQLException {
        return wrapped.getObject(columnName);
    }

    /**
     * @param colName
     * @param map
     * @return
     * @throws java.sql.SQLException
     */
    public Object getObject(String colName, Map map) throws SQLException {
        return wrapped.getObject(colName, map);
    }

    /**
     * @param i
     * @return
     * @throws java.sql.SQLException
     */
    public Ref getRef(int i) throws SQLException {
        return wrapped.getRef(i);
    }

    /**
     * @param colName
     * @return
     * @throws java.sql.SQLException
     */
    public Ref getRef(String colName) throws SQLException {
        return wrapped.getRef(colName);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public int getRow() throws SQLException {
        return wrapped.getRow();
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public short getShort(int columnIndex) throws SQLException {
        return wrapped.getShort(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public short getShort(String columnName) throws SQLException {
        return wrapped.getShort(columnName);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public Statement getStatement() throws SQLException {
        return wrapped.getStatement();
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public String getString(int columnIndex) throws SQLException {
        return wrapped.getString(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public String getString(String columnName) throws SQLException {
        return wrapped.getString(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public Time getTime(int columnIndex) throws SQLException {
        return wrapped.getTime(columnIndex);
    }

    /**
     * @param columnIndex
     * @param cal
     * @return
     * @throws java.sql.SQLException
     */
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return wrapped.getTime(columnIndex, cal);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public Time getTime(String columnName) throws SQLException {
        return wrapped.getTime(columnName);
    }

    /**
     * @param columnName
     * @param cal
     * @return
     * @throws java.sql.SQLException
     */
    public Time getTime(String columnName, Calendar cal) throws SQLException {
        return wrapped.getTime(columnName, cal);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return wrapped.getTimestamp(columnIndex);
    }

    /**
     * @param columnIndex
     * @param cal
     * @return
     * @throws java.sql.SQLException
     */
    public Timestamp getTimestamp(int columnIndex, Calendar cal)
        throws SQLException {
        return wrapped.getTimestamp(columnIndex, cal);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public Timestamp getTimestamp(String columnName) throws SQLException {
        return wrapped.getTimestamp(columnName);
    }

    /**
     * @param columnName
     * @param cal
     * @return
     * @throws java.sql.SQLException
     */
    public Timestamp getTimestamp(String columnName, Calendar cal)
        throws SQLException {
        return wrapped.getTimestamp(columnName, cal);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public int getType() throws SQLException {
        return wrapped.getType();
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     * @deprecated
     */
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return wrapped.getUnicodeStream(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     * @deprecated
     */
    public InputStream getUnicodeStream(String columnName)
        throws SQLException {
        return wrapped.getUnicodeStream(columnName);
    }

    /**
     * @param columnIndex
     * @return
     * @throws java.sql.SQLException
     */
    public URL getURL(int columnIndex) throws SQLException {
        return wrapped.getURL(columnIndex);
    }

    /**
     * @param columnName
     * @return
     * @throws java.sql.SQLException
     */
    public URL getURL(String columnName) throws SQLException {
        return wrapped.getURL(columnName);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public SQLWarning getWarnings() throws SQLException {
        return wrapped.getWarnings();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return wrapped.hashCode();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void insertRow() throws SQLException {
        wrapped.insertRow();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean isAfterLast() throws SQLException {
        return wrapped.isAfterLast();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean isBeforeFirst() throws SQLException {
        return wrapped.isBeforeFirst();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean isFirst() throws SQLException {
        return wrapped.isFirst();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean isLast() throws SQLException {
        return wrapped.isLast();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean last() throws SQLException {
        return wrapped.last();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void moveToCurrentRow() throws SQLException {
        wrapped.moveToCurrentRow();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void moveToInsertRow() throws SQLException {
        wrapped.moveToInsertRow();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean next() throws SQLException {
        return wrapped.next();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean previous() throws SQLException {
        return wrapped.previous();
    }

    /**
     * @throws java.sql.SQLException
     */
    public void refreshRow() throws SQLException {
        wrapped.refreshRow();
    }

    /**
     * @param rows
     * @return
     * @throws java.sql.SQLException
     */
    public boolean relative(int rows) throws SQLException {
        return wrapped.relative(rows);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean rowDeleted() throws SQLException {
        return wrapped.rowDeleted();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean rowInserted() throws SQLException {
        return wrapped.rowInserted();
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean rowUpdated() throws SQLException {
        return wrapped.rowUpdated();
    }

    /**
     * @param direction
     * @throws java.sql.SQLException
     */
    public void setFetchDirection(int direction) throws SQLException {
        wrapped.setFetchDirection(direction);
    }

    /**
     * @param rows
     * @throws java.sql.SQLException
     */
    public void setFetchSize(int rows) throws SQLException {
        wrapped.setFetchSize(rows);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return wrapped.toString();
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateArray(int columnIndex, Array x) throws SQLException {
        wrapped.updateArray(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateArray(String columnName, Array x) throws SQLException {
        wrapped.updateArray(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @param length
     * @throws java.sql.SQLException
     */
    public void updateAsciiStream(int columnIndex, InputStream x, int length)
        throws SQLException {
        wrapped.updateAsciiStream(columnIndex, x, length);
    }

    /**
     * @param columnName
     * @param x
     * @param length
     * @throws java.sql.SQLException
     */
    public void updateAsciiStream(String columnName, InputStream x, int length)
        throws SQLException {
        wrapped.updateAsciiStream(columnName, x, length);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBigDecimal(int columnIndex, BigDecimal x)
        throws SQLException {
        wrapped.updateBigDecimal(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBigDecimal(String columnName, BigDecimal x)
        throws SQLException {
        wrapped.updateBigDecimal(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @param length
     * @throws java.sql.SQLException
     */
    public void updateBinaryStream(int columnIndex, InputStream x, int length)
        throws SQLException {
        wrapped.updateBinaryStream(columnIndex, x, length);
    }

    /**
     * @param columnName
     * @param x
     * @param length
     * @throws java.sql.SQLException
     */
    public void updateBinaryStream(
        String columnName,
        InputStream x,
        int length)
        throws SQLException {
        wrapped.updateBinaryStream(columnName, x, length);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        wrapped.updateBlob(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBlob(String columnName, Blob x) throws SQLException {
        wrapped.updateBlob(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        wrapped.updateBoolean(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBoolean(String columnName, boolean x)
        throws SQLException {
        wrapped.updateBoolean(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateByte(int columnIndex, byte x) throws SQLException {
        wrapped.updateByte(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateByte(String columnName, byte x) throws SQLException {
        wrapped.updateByte(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        wrapped.updateBytes(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateBytes(String columnName, byte[] x) throws SQLException {
        wrapped.updateBytes(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @param length
     * @throws java.sql.SQLException
     */
    public void updateCharacterStream(int columnIndex, Reader x, int length)
        throws SQLException {
        wrapped.updateCharacterStream(columnIndex, x, length);
    }

    /**
     * @param columnName
     * @param reader
     * @param length
     * @throws java.sql.SQLException
     */
    public void updateCharacterStream(
        String columnName,
        Reader reader,
        int length)
        throws SQLException {
        wrapped.updateCharacterStream(columnName, reader, length);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        wrapped.updateClob(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateClob(String columnName, Clob x) throws SQLException {
        wrapped.updateClob(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateDate(int columnIndex, Date x) throws SQLException {
        wrapped.updateDate(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateDate(String columnName, Date x) throws SQLException {
        wrapped.updateDate(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateDouble(int columnIndex, double x) throws SQLException {
        wrapped.updateDouble(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateDouble(String columnName, double x) throws SQLException {
        wrapped.updateDouble(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateFloat(int columnIndex, float x) throws SQLException {
        wrapped.updateFloat(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateFloat(String columnName, float x) throws SQLException {
        wrapped.updateFloat(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateInt(int columnIndex, int x) throws SQLException {
        wrapped.updateInt(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateInt(String columnName, int x) throws SQLException {
        wrapped.updateInt(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateLong(int columnIndex, long x) throws SQLException {
        wrapped.updateLong(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateLong(String columnName, long x) throws SQLException {
        wrapped.updateLong(columnName, x);
    }

    /**
     * @param columnIndex
     * @throws java.sql.SQLException
     */
    public void updateNull(int columnIndex) throws SQLException {
        wrapped.updateNull(columnIndex);
    }

    /**
     * @param columnName
     * @throws java.sql.SQLException
     */
    public void updateNull(String columnName) throws SQLException {
        wrapped.updateNull(columnName);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateObject(int columnIndex, Object x) throws SQLException {
        wrapped.updateObject(columnIndex, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @param scale
     * @throws java.sql.SQLException
     */
    public void updateObject(int columnIndex, Object x, int scale)
        throws SQLException {
        wrapped.updateObject(columnIndex, x, scale);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateObject(String columnName, Object x) throws SQLException {
        wrapped.updateObject(columnName, x);
    }

    /**
     * @param columnName
     * @param x
     * @param scale
     * @throws java.sql.SQLException
     */
    public void updateObject(String columnName, Object x, int scale)
        throws SQLException {
        wrapped.updateObject(columnName, x, scale);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        wrapped.updateRef(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateRef(String columnName, Ref x) throws SQLException {
        wrapped.updateRef(columnName, x);
    }

    /**
     * @throws java.sql.SQLException
     */
    public void updateRow() throws SQLException {
        wrapped.updateRow();
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateShort(int columnIndex, short x) throws SQLException {
        wrapped.updateShort(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateShort(String columnName, short x) throws SQLException {
        wrapped.updateShort(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateString(int columnIndex, String x) throws SQLException {
        wrapped.updateString(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateString(String columnName, String x) throws SQLException {
        wrapped.updateString(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateTime(int columnIndex, Time x) throws SQLException {
        wrapped.updateTime(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateTime(String columnName, Time x) throws SQLException {
        wrapped.updateTime(columnName, x);
    }

    /**
     * @param columnIndex
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateTimestamp(int columnIndex, Timestamp x)
        throws SQLException {
        wrapped.updateTimestamp(columnIndex, x);
    }

    /**
     * @param columnName
     * @param x
     * @throws java.sql.SQLException
     */
    public void updateTimestamp(String columnName, Timestamp x)
        throws SQLException {
        wrapped.updateTimestamp(columnName, x);
    }

    /**
     * @return
     * @throws java.sql.SQLException
     */
    public boolean wasNull() throws SQLException {
        return wrapped.wasNull();
    }

}
