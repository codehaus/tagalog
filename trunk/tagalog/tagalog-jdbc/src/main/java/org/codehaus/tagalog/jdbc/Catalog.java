/*
 * $Id: Catalog.java,v 1.13 2004-12-17 13:09:43 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.tagalog.ParseError;
import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.sax.TagalogSAXParserFactory;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

import org.codehaus.tagalog.jdbc.tags.CatalogTagLibrary;

/**
 * A collection of named procedures, which are in turn groups of SQL
 * statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.13 $
 */
public final class Catalog {
    private PlexusContainer container;

    private Map procedures = new java.util.HashMap();

    public Catalog(PlexusContainer container, URL resourceURL)
        throws Exception
    {
        if (resourceURL == null)
            throw new NullPointerException("null resource URL");
        this.container = container;
        parse(resourceURL);
    }

    private static ParserConfiguration parserConfiguration;

    private static TagalogSAXParserFactory factory;

    private ParseError[] parseErrors;

    /**
     * Parse an XML resource containing procedure definitions and load the
     * procedures into the catalog. If the resource contains errors these
     * will be made available from {@link #parseErrors()} and the catalog
     * will be emptied of procedure definitions.
     *
     * @param resourceURL The URL of the resource to load.
     * @throws Exception
     */
    public synchronized void parse(URL resourceURL) throws Exception {
        if (factory == null) {
            parserConfiguration = new ParserConfiguration();
            parserConfiguration.addTagLibrary(CatalogTagLibrary.NS_URI,
                                              new CatalogTagLibrary());
            parserConfiguration.setDefaultNamespace(CatalogTagLibrary.NS_URI);
            factory = new TagalogSAXParserFactory(parserConfiguration);
        }
        InputStream input = resourceURL.openStream();
        HashMap context = new HashMap();
        context.put("catalog", this);
        TagalogParser parser = factory.createParser(input);
        parser.parse(context);
        parseErrors = parser.parseErrors();
        if (parseErrors.length > 0)
            procedures.clear();
    }

    /**
     * Retrieve an array of {@link ParseError}s from the last call to
     * {@link #parse}.
     *
     * @return A possibly empty list of parse errors.
     */
    public ParseError[] parseErrors() {
        return parseErrors;
    }

    /**
     * Put a procedure into the catalog.
     *
     * @param proc The procedure to put into the catalog.
     */
    public void addProc(Proc proc) {
        String name = proc.getName();
        procedures.put(name, proc);
    }

    //
    // Getting connections from the container.
    //

    private ConnectionManager defaultConnectionManager;

    public synchronized ConnectionManager getConnectionManager()
        throws ComponentLookupException
    {
        if (defaultConnectionManager == null) {
            Object o = container.lookup(ConnectionManager.ROLE);
            defaultConnectionManager = (ConnectionManager) o;
        }
        return defaultConnectionManager;
    }

    public String getDialect() throws ComponentLookupException {
        return getConnectionManager().getDialect();
    }

    private Map namedConnectionManagers;

    public synchronized ConnectionManager getConnectionManager(String connectionName)
        throws ComponentLookupException
    {
        if (connectionName == null)
            throw new NullPointerException("null connection name");
        if (namedConnectionManagers == null)
            namedConnectionManagers = new java.util.HashMap();

        Object o = namedConnectionManagers.get(connectionName);
        if (o == null) {
            o = container.lookup(ConnectionManager.ROLE, connectionName);
            namedConnectionManagers.put(connectionName, o);
        }
        return (ConnectionManager) o;
    }

    public String getDialect(String connectionManager)
        throws ComponentLookupException
    {
        return getConnectionManager(connectionManager).getDialect();
    }

    //
    // Running procedures
    //

    public void run(String procId) throws ProcException {
        run(procId, new ProcContext());
    }

    public void run(String procId, ProcContext ctx) throws ProcException {
        Object result = execute(procId, ctx);
        if (result != null)
            throw new ProcException("unexpected result " + result);
    }

    public ResultSet query(String procId) throws ProcException {
        return query(procId, new ProcContext());
    }

    public ResultSet query(String procId, ProcContext ctx)
        throws ProcException
    {
        return (ResultSet) execute(procId, ctx);
    }

    public Integer update(String procId) throws ProcException {
        return update(procId, new ProcContext());
    }

    public Integer update(String procId, ProcContext ctx)
        throws ProcException
    {
        return (Integer) execute(procId, ctx);
    }

    public Object execute(String procId) throws ProcException {
        return execute(procId, new ProcContext());
    }

    public Object execute(String procId, ProcContext ctx) throws ProcException {
        Proc proc = (Proc) procedures.get(procId);
        if (proc == null)
            throw new IllegalArgumentException("unknown proc '"
                                               + procId + "'");
        return proc.execute(this, ctx);
    }
}
