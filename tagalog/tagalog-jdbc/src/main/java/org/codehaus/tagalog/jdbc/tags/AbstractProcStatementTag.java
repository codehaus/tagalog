/*
 * $Id: AbstractProcStatementTag.java,v 1.3 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.CompoundProcStatement;
import org.codehaus.tagalog.jdbc.ProcStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public abstract class AbstractProcStatementTag extends AbstractTag {
    protected boolean rootTag = false;

    protected ProcStatement stmt;

    public Object end(String elementName) throws TagException {
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