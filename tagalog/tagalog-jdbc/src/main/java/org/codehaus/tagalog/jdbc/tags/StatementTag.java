/*
 * $Id: StatementTag.java,v 1.1 2004-02-11 19:03:21 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc.tagalog;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagalogParseException;

import com.fintricity.jdbc.QueryStatement;
import com.fintricity.jdbc.QueryType;
import com.fintricity.jdbc.SQLStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public final class StatementTag extends AbstractTag {
    SQLStatement stmt;

    StringBuffer buffer;

    public void begin(String elementName, Attributes attributes)
        throws TagalogParseException
    {
        String s;

        if (elementName.equals("stmt"))
            stmt = new SQLStatement();
        else if (elementName.equals("query"))
            stmt = new QueryStatement();
        else
            throw new TagalogParseException("invalid element " + elementName);
        s = attributes.getValue("dialect");
        if (s != null)
            stmt.setDialect(s);
        if (stmt instanceof QueryStatement) {
            s = attributes.getValue("rows");
            if (s != null) {
                QueryStatement qStmt = (QueryStatement) stmt;
                qStmt.setQueryType(QueryType.fromString(s));
            }
        }
        buffer = new StringBuffer();
    }

    public void text(char[] characters, int start, int length) {
        buffer.append(characters, start, length);
    }

    public Object end(String elementName) throws TagalogParseException {
        StatementGroupTag parent;

        stmt.setSQLTemplate(buffer.toString());
        parent = (StatementGroupTag) requireAncestor("statement group",
                                                     StatementGroupTag.class);
        parent.stmt.addStatement(stmt);
        return stmt;
    }

    public boolean recycle() {
        stmt = null;
        buffer = null;
        return true;
    }
}
