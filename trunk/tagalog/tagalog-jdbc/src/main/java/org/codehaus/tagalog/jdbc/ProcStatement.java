/*
 * $Id: ProcStatement.java,v 1.2 2004-01-30 17:48:58 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public interface ProcStatement {
    Object[] EMPTY_ARRAY = new ProcStatement[0];

    /**
     * Execute a statement
     *
     * @param catalog The catalog that the statement belongs to.
     * @param proc The procedure that the statement belongs to.
     * @param ctx Context object for this invocation, carrying attribute
     * values.
     */
    Object execute(Catalog catalog, Proc proc, ProcContext ctx)
        throws ProcException;
}