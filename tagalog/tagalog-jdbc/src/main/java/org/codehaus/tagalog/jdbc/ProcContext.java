/*
 * $Id: ProcContext.java,v 1.2 2004-01-23 16:40:16 mhw Exp $
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
import java.util.LinkedList;
import java.util.List;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

/**
 * The context within which a procedure will be executed.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public final class ProcContext {
    /**
     * Name-value pair in the context.
     */
    private static final class NameValue {
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

    // Connection management.

    private String connectionName;

    /**
     * Set the name of the connection that statements running in this context
     * will use.
     *
     * @param connectionName
     */
    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    private ConnectionManager connectionManager;

    public synchronized ConnectionManager getConnectionManager(Catalog catalog)
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

    public String getDialect(Catalog catalog) throws ComponentLookupException {
        return getConnectionManager(catalog).getDialect();
    }

    private Connection connection;

    public synchronized Connection getConnection(Catalog catalog)
        throws SQLException, ComponentLookupException
    {
         if (connection == null) {
             connection = getConnectionManager(catalog).getConnection();
         }
         return connection;
    }
}
