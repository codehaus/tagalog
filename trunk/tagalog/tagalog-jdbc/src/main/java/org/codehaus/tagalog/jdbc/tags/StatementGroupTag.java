/*
 * $Id: StatementGroupTag.java,v 1.1 2004-02-11 19:03:21 mhw Exp $
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

import com.fintricity.jdbc.DialectChoiceStatement;
import com.fintricity.jdbc.StatementGroup;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class StatementGroupTag extends AbstractTag {
    StatementGroup stmt;

    public void begin(String elementName, Attributes attributes)
        throws TagalogParseException
    {
        if (elementName.equals("dialect-choice"))
            stmt = new DialectChoiceStatement();
        else
            throw new TagalogParseException("unknown element " + elementName);
    }

    public Object end(String elementName) throws TagalogParseException {
        StatementGroupTag parent;

        stmt.closeStatementList();
        parent = (StatementGroupTag) findAncestorWithClass(StatementGroupTag.class);
        if (parent != null) {
            parent.stmt.addStatement(stmt);
            return null;
        } else {
            return stmt;
        }
    }

    public boolean recycle() {
        stmt = null;
        return true;
    }
}
