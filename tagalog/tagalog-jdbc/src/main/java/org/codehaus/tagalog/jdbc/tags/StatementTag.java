/*
 * $Id: StatementTag.java,v 1.3 2004-02-26 12:32:26 mhw Exp $
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

import com.fintricity.jdbc.AbstractProcStatement;
import com.fintricity.jdbc.AbstractSQLStatement;
import com.fintricity.jdbc.QueryStatement;
import com.fintricity.jdbc.QueryType;
import com.fintricity.jdbc.SQLStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class StatementTag extends AbstractProcStatementTag {
    StringBuffer buffer;

    public void begin(String elementName, Attributes attributes)
        throws TagalogParseException
    {
        String s;

        if (elementName.equals("stmt")) {
            SQLStatement sqlStmt = new SQLStatement();
            s = attributes.getValue("generates-keys");
            if (s != null && s.equals("true"))
                sqlStmt.setGeneratesKeys(true);
            stmt = sqlStmt;
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
            ((AbstractProcStatement) stmt).setDialect(s);
        buffer = new StringBuffer();
    }

    public void text(char[] characters, int start, int length) {
        buffer.append(characters, start, length);
    }

    public Object end(String elementName) throws TagalogParseException {
        ((AbstractSQLStatement) stmt).setSQLTemplate(buffer.toString());
        return super.end(elementName);
    }

    public boolean recycle() {
        buffer = null;
        return super.recycle();
    }
}
