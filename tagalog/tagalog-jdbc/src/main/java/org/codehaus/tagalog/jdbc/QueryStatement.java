/*
 * $Id: QueryStatement.java,v 1.2 2004-01-23 18:49:24 mhw Exp $
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
 * @version $Revision: 1.2 $
 */
public final class QueryStatement extends SQLStatement {
    public void setQueryType(QueryType queryType) {
        super.setQueryType(queryType);
    }
}
