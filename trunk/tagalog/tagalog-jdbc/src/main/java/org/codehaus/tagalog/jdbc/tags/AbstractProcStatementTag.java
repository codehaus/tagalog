/*
 * $Id: AbstractProcStatementTag.java,v 1.1 2004-02-26 12:32:26 mhw Exp $
 *
 * Copyright (c) 2004 Fintricity Limited. All Rights Reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF FINTRICITY LIMITED
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 */

package com.fintricity.jdbc.tagalog;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagalogParseException;

import com.fintricity.jdbc.CompoundProcStatement;
import com.fintricity.jdbc.ProcStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public abstract class AbstractProcStatementTag extends AbstractTag {
    protected boolean rootTag = false;

    protected ProcStatement stmt;

    public Object end(String elementName) throws TagalogParseException {
        Tag tag;
        AbstractCompoundStatementTag parent;
        CompoundProcStatement parentCompoundStmt;

        if (rootTag) {
            tag = findAncestorWithClass(AbstractCompoundStatementTag.class);
            if (tag == null)
                return stmt;
        }

        tag = requireAncestor("compound statement",
                              AbstractCompoundStatementTag.class);
        parent = (AbstractCompoundStatementTag) tag; 
        parentCompoundStmt = (CompoundProcStatement) parent.stmt;
        parentCompoundStmt.addStatement(stmt);
        return stmt;
    }

    public boolean recycle() {
        stmt = null;
        return true;
    }
}