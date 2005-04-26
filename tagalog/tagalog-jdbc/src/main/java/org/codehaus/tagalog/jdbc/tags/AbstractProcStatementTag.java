/*
 * $Id: AbstractProcStatementTag.java,v 1.6 2005-04-26 17:13:54 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.NodeHandler;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.AbstractProcStatement;
import org.codehaus.tagalog.jdbc.CompoundProcStatement;
import org.codehaus.tagalog.jdbc.ProcStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.6 $
 */
public abstract class AbstractProcStatementTag extends AbstractTag {
    protected boolean rootTag = false;

    private ProcStatement stmt;

    protected final ProcStatement getStatement() {
        return this.stmt;
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        stmt = createProcStatement(elementName, attributes);
        if (stmt == null)
            throw new TagException("invalid element " + elementName);

        String s = attributes.getValue("dialect");
        if (s != null)
            ((AbstractProcStatement) stmt).setDialect(s);
    }

    protected abstract ProcStatement createProcStatement(String elementName,
                                                         Attributes attributes)
        throws TagException;

    public Object end(String elementName) throws TagException {
        NodeHandler nodeHandler;
        AbstractCompoundStatementTag parent;
        CompoundProcStatement parentCompoundStmt;

        if (rootTag) {
            nodeHandler = findAncestor(AbstractCompoundStatementTag.class);
            if (nodeHandler == null)
                return stmt;
        }

        nodeHandler = requireAncestor("compound statement",
                                      AbstractCompoundStatementTag.class);
        parent = (AbstractCompoundStatementTag) nodeHandler;
        parentCompoundStmt = (CompoundProcStatement) parent.getStatement();
        parentCompoundStmt.addStatement(stmt);
        return stmt;
    }

    public boolean recycle() {
        stmt = null;
        return true;
    }
}
