/*
 * $Id: DataSourceConnectionManager.java,v 1.2 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public class DataSourceConnectionManager
    extends AbstractConnectionManager
    implements ConnectionManager, Configurable
{
    private static final String DATA_SOURCE = "data-source";

    private DataSource dataSource;

    public void configure(Configuration configuration)
        throws ConfigurationException
    {
        String driver = configuration.getChild(DATA_SOURCE).getValue();
        try {
            dataSource = (DataSource) Class.forName(driver).newInstance();
        } catch (Exception e) {
            throw new ConfigurationException(
                "could not load data source " + driver, e);
        }
        try {
            computeDialect(driver);
        } catch (IOException e) {
            throw new ConfigurationException(
                "could not compute dialect for data source " + driver, e);
        }

        configureDataSource(configuration);
    }

    private void configureDataSource(Configuration configuration)
        throws ConfigurationException
    {
        Configuration[] settings = configuration.getChildren();

        for (int i = 0; i < settings.length; i++) {
            Configuration c = settings[i];
            String property = c.getName();
            if (property.equals(DATA_SOURCE)) {
                continue;
            }
            String value = c.getValue();
            try {
                PropertyUtils.setSimpleProperty(dataSource, property, value);
            } catch (Exception e) {
                throw new ConfigurationException(
                    "could not set property" + property, e);
            }
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
