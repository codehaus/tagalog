/*
 * $Id: QueryStatement.java,v 1.1 2004-01-23 15:21:36 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class QueryStatement extends SQLStatement {
    private QueryType type;

    public void setQueryType(QueryType type) {
        this.type = type;
    }
}
