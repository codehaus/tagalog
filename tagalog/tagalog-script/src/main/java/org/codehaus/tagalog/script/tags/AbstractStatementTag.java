/*
 * $Id: AbstractStatementTag.java,v 1.1 2004-11-04 18:04:57 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.script.CompoundStatement;
import org.codehaus.tagalog.script.Statement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public abstract class AbstractStatementTag
    extends AbstractTag
{
    protected boolean rootTag = false;

    protected Statement stmt;

    public Object end(String elementName) throws TagException {
        Tag tag;
        AbstractStatementTag parent;
        CompoundStatement parentCompoundStmt;

        if (rootTag) {
            tag = findAncestorWithClass(AbstractCompoundStatementTag.class);
            if (tag == null)
                return stmt;
        }

        tag = requireAncestor("compound statement",
                              AbstractCompoundStatementTag.class);
        parent = (AbstractCompoundStatementTag) tag; 
        parentCompoundStmt = (CompoundStatement) parent.stmt;
        parentCompoundStmt.addStatement(stmt);
        return stmt;
    }

    public boolean recycle() {
        stmt = null;
        return true;
    }
}
