/*
 * $Id: Proc.java,v 1.4 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * Representation of a procedure, which attaches a name and a connection
 * to a {@link ProcStatement}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public final class Proc {
    private String name;

    private String connectionName;

    private ProcStatement body;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setBody(ProcStatement body) {
        this.body = body;
    }

    public Object execute(Catalog catalog, ProcContext ctx)
        throws ProcException
    {
        Object result = null;

        ctx.setConnectionName(connectionName);
        try {
            ctx.begin();
            result = body.execute(catalog, this, ctx);
        } finally {
            ctx.end();
        }
        return result;
    }
}
