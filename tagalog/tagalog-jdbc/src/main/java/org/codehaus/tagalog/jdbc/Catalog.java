/*
 * $Id: Catalog.java,v 1.6 2004-02-11 18:38:59 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Map;

import org.codehaus.plexus.PlexusContainer;

import com.fintricity.jdbc.xstream.CatalogXStream;

/**
 * A collection of named procedures, which are in turn groups of SQL
 * statements.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
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

    public void parse(URL resourceURL) throws Exception {
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
