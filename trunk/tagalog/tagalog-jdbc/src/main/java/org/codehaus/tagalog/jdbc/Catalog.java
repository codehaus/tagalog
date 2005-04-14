/*
 * $Id: Catalog.java,v 1.14 2005-04-14 09:27:53 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.tagalog.ParseError;
import org.codehaus.tagalog.ParseFailedException;
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
 * @version $Revision: 1.14 $
 */
public final class Catalog {
    private PlexusContainer container;

    private Map procedures = new java.util.HashMap();

    /**
     * Initialise an empty <code>Catalog</code>.
     *
     * @param container Container to retrieve connections from.
     */
    public Catalog(PlexusContainer container) {
        if (container == null)
            throw new NullPointerException("container is null");
        this.container = container;
    }

    /**
     * Initialise the <code>Catalog</code> with procedures from an XML
     * resource. If the resource contains errors a
     * {@link ParseFailedException} will be thrown. The parse errors can be
     * retrieved from the thrown exception.
     *
     * @param container Container to retrieve connections from.
     * @param resourceURL The URL of the resource to load.
     * @throws ParseFailedException if the resource could not be parsed.
     * @throws Exception if there were other problems accessing or parsing
     *         the resource.
     */
    public Catalog(PlexusContainer container, URL resourceURL)
        throws Exception
    {
        this(container);
        if (resourceURL == null)
            throw new NullPointerException("null resource URL");
        parse(resourceURL);
    }

    private static ParserConfiguration parserConfiguration;

    private static TagalogSAXParserFactory factory;

    /**
     * Parse an XML resource containing procedure definitions and load the
     * procedures into the catalog. If the resource contains errors a
     * {@link ParseFailedException} will be thrown and the catalog
     * will be emptied of procedure definitions. The parse errors can be
     * retrieved from the thrown exception.
     *
     * @param resourceURL The URL of the resource to load.
     * @throws ParseFailedException if the resource could not be parsed.
     * @throws Exception if there were other problems accessing or parsing
     *         the resource.
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

        ParseError[] parseErrors = parser.parseErrors();
        if (parseErrors.length > 0) {
            procedures.clear();
            throw new ParseFailedException("failed to parse " + resourceURL,
                                           parseErrors);
        }
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
