/*
 * $Id: Catalog.java,v 1.11 2004-11-02 12:11:46 mhw Exp $
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

import org.codehaus.tagalog.jdbc.tags.CatalogTagLibrary;

/**
 * A collection of named procedures, which are in turn groups of SQL
 * statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.11 $
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
     * @return A possibly empty list of parse errors, or <code>null</code> if
     * there were no errors.
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

    public PlexusContainer getContainer() {
        return container;
    }

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
