/*
 * $Id: Catalog.java,v 1.1 2004-01-23 15:21:36 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.net.URL;
import java.util.Map;

import org.codehaus.plexus.PlexusContainer;

import com.fintricity.jdbc.xstream.CatalogXStream;

/**
 * A collection of named Jelly procedures.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class Catalog {
    public static final String CATALOG = "catalog";

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

    public void parse(URL resourceURL) throws Exception {
/*
        XMLOutput output;
        CatalogJellyContext parseContext;

        output = XMLOutput.createDummyXMLOutput();
        parseContext = new CatalogJellyContext();
        parseContext.registerTagLibrary(CatalogTagLibrary.NAMESPACE_URI,
                                        new CatalogTagLibrary());
        parseContext.setVariable(CATALOG, this);
        parseContext.runScript(resourceURL, output);
*/
        CatalogXStream xstream = new CatalogXStream();
        xstream.parse(resourceURL, this);
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

    public void execute(String procId, ProcContext ctx) throws ProcException {
        Proc proc = (Proc) procedures.get(procId);
        if (proc == null)
            throw new IllegalArgumentException("unknown proc '"
                                               + procId + "'");
        proc.execute(this, ctx);
    }
}
