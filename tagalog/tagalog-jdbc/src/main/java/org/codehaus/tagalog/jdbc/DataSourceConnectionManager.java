/*
 * $Id: DataSourceConnectionManager.java,v 1.3 2004-02-06 12:10:45 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;

import org.codehaus.plexus.configuration.Property;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public class DataSourceConnectionManager
    extends AbstractConnectionManager
    implements ConnectionManager, Initializable
{
    private String dataSourceClass;

    private Property[] properties;

    private DataSource dataSource;

    public void initialize() throws Exception {
        dataSource = (DataSource) Class.forName(dataSourceClass).newInstance();
        computeDialect(dataSourceClass);

        for (int i = 0; i < properties.length; i++) {
            Property c = properties[i];
            String name = c.getName();
            String value = c.getValue();
            PropertyUtils.setSimpleProperty(dataSource, name, value);
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
