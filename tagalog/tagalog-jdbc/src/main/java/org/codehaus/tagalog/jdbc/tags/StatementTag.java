/*
 * $Id: StatementTag.java,v 1.6 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.AbstractProcStatement;
import org.codehaus.tagalog.jdbc.AbstractSQLStatement;
import org.codehaus.tagalog.jdbc.QueryStatement;
import org.codehaus.tagalog.jdbc.QueryType;
import org.codehaus.tagalog.jdbc.SQLStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
 */
public final class StatementTag extends AbstractProcStatementTag {
    StringBuffer buffer;

    public void begin(String elementName, Attributes attributes)
        throws TagException
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
            throw new TagException("invalid element " + elementName);
        s = attributes.getValue("dialect");
        if (s != null)
            ((AbstractProcStatement) stmt).setDialect(s);
        buffer = new StringBuffer();
    }

    public void text(char[] characters, int start, int length) {
        buffer.append(characters, start, length);
    }

    public Object end(String elementName) throws TagException {
        ((AbstractSQLStatement) stmt).setSQLTemplate(buffer.toString());
        return super.end(elementName);
    }

    public boolean recycle() {
        buffer = null;
        return super.recycle();
    }
}
