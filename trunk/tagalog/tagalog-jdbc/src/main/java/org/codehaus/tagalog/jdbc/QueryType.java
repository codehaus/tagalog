/*
 * $Id: QueryType.java,v 1.1 2004-01-23 15:21:36 mhw Exp $
 *
 * Copyright (c) 2003 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc;

import java.util.Map;

/**
 * Types of query. The values from this enumeration indicate how many
 * rows are expected as a result from a query.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class QueryType {
    private String name;

    private QueryType(String name) {
        this.name = name;
        namesToQueryTypes.put(name, this);
    }

    public String toString() {
        return name;
    }

    private static final Map namesToQueryTypes = new java.util.HashMap();

    public static QueryType fromString(String name) {
        return (QueryType) namesToQueryTypes.get(name);
    }

    public static final QueryType ZERO_OR_ONE = new QueryType("zero-or-one");

    public static final QueryType ONE = new QueryType("one");

    public static final QueryType ZERO_OR_MORE = new QueryType("zero-or-more");

    public static final QueryType ONE_OR_MORE = new QueryType("one-or-more");
}
