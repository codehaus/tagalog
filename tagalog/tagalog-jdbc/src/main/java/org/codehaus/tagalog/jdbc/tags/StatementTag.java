/*
 * $Id: StatementTag.java,v 1.2 2004-02-25 18:14:23 mhw Exp $
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
 * @version $Revision: 1.2 $
 */
public final class StatementTag extends AbstractTag {
    SQLStatement stmt;

    StringBuffer buffer;

    public void begin(String elementName, Attributes attributes)
        throws TagalogParseException
    {
        String s;

        if (elementName.equals("stmt")) {
            stmt = new SQLStatement();
            s = attributes.getValue("generates-keys");
            if (s != null && s.equals("true"))
                stmt.setGeneratesKeys(true);
        } else if (elementName.equals("query")) {
            QueryStatement qStmt = new QueryStatement();
            s = attributes.getValue("rows");
            if (s != null)
                qStmt.setQueryType(QueryType.fromString(s));
            stmt = qStmt;
        } else
            throw new TagalogParseException("invalid element " + elementName);
        s = attributes.getValue("dialect");
        if (s != null)
            stmt.setDialect(s);
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
