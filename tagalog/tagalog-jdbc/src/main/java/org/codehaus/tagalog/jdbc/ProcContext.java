/*
 * $Id: ProcContext.java,v 1.3 2004-01-23 18:49:24 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

/**
 * The context within which a procedure will be executed.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class ProcContext {
    /**
     * Name-value pair in the context.
     */
    public static final class NameValue {
        String name;
        String value;

        NameValue(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    private List attributes = new LinkedList();

    public void setAttribute(String name, String value) {
        if (name == null)
            throw new NullPointerException("name is null");
        attributes.add(new NameValue(name, value));
    }

    public Iterator attributeIterator() {
        return Collections.unmodifiableList(attributes).iterator();
    }

    // Connection management.

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
        if (references == 0) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ProcException("close failed", e);
            } finally {
                connection = null;
            }
        }
    }
}
