/*
 * $Id: AbstractProcStatement.java,v 1.1 2004-02-26 12:27:49 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * Abstract base class implementing the basic contract of
 * {@link ProcStatement}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public abstract class AbstractProcStatement implements ProcStatement {

    protected String dialect;

    public final void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public final String getDialect() {
        return dialect;
    }
}
