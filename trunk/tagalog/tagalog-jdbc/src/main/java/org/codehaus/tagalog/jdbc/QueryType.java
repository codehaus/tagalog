/*
 * $Id: QueryType.java,v 1.6 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc;

import java.util.Map;

/**
 * Types of query. The values from this enumeration indicate how many
 * rows are expected as a result from a query.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
 */
public final class QueryType {
    private final String name;

    private final int minimumRowCount;

    private QueryType(String name, int minimumRowCount) {
        this.name = name;
        this.minimumRowCount = minimumRowCount;
        namesToQueryTypes.put(name, this);
    }

    public String toString() {
        return name;
    }

    public int getMinimumRowCount() {
        return minimumRowCount;
    }

    private static final Map namesToQueryTypes = new java.util.HashMap();

    public static QueryType fromString(String name) {
        QueryType type = (QueryType) namesToQueryTypes.get(name);
        if (type == null)
            throw new IllegalArgumentException(name
                                               + " is not a valid query type");
        return type;
    }

    public static final QueryType ZERO        = new QueryType("zero", 0);

    public static final QueryType ZERO_OR_ONE = new QueryType("zero-or-one", 0);

    public static final QueryType ONE         = new QueryType("one", 1);

    public static final QueryType ZERO_OR_MORE
                                              = new QueryType("zero-or-more", 0);

    public static final QueryType ONE_OR_MORE = new QueryType("one-or-more", 1);
}
