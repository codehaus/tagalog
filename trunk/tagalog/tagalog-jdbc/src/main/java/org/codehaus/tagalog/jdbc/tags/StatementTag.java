/*
 * $Id: StatementTag.java,v 1.8 2004-12-17 13:43:04 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.AbstractSQLStatement;
import org.codehaus.tagalog.jdbc.ProcStatement;
import org.codehaus.tagalog.jdbc.QueryStatement;
import org.codehaus.tagalog.jdbc.QueryType;
import org.codehaus.tagalog.jdbc.SQLStatement;
import org.codehaus.tagalog.jdbc.UpdateStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.8 $
 */
public final class StatementTag extends AbstractProcStatementTag {
    StringBuffer buffer;

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        super.begin(elementName, attributes);

        buffer = new StringBuffer();
    }

    protected ProcStatement createProcStatement(String elementName,
                                                Attributes attributes)
    {
        String s;

        if (elementName.equals("stmt")) {
            SQLStatement sqlStmt = new SQLStatement();
            s = attributes.getValue("generates-keys");
            if (s != null && s.equals("true"))
                sqlStmt.setGeneratesKeys(true);
            return sqlStmt;
        } else if (elementName.equals("query")) {
            QueryStatement qStmt = new QueryStatement();
            s = attributes.getValue("rows");
            if (s != null)
                qStmt.setQueryType(QueryType.fromString(s));
            return qStmt;
        } else if (elementName.equals("update")) {
            UpdateStatement uStmt = new UpdateStatement();
            s = attributes.getValue("generates-keys");
            if (s != null && s.equals("true"))
                uStmt.setGeneratesKeys(true);
            return uStmt;
        }
        return null;
    }

    public void text(char[] characters, int start, int length) {
        buffer.append(characters, start, length);
    }

    public Object end(String elementName) throws TagException {
        AbstractSQLStatement statement = (AbstractSQLStatement) getStatement();
        statement.setSQLTemplate(buffer.toString());
        return super.end(elementName);
    }

    public boolean recycle() {
        buffer = null;
        return super.recycle();
    }
}
