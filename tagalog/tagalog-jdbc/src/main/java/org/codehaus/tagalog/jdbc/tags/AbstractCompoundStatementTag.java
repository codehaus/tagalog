/*
 * $Id: AbstractCompoundStatementTag.java,v 1.4 2004-12-17 13:43:04 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.CompoundProcStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public abstract class AbstractCompoundStatementTag
    extends AbstractProcStatementTag
{
    public Object end(String elementName) throws TagException {
        CompoundProcStatement statement = (CompoundProcStatement) getStatement();
        statement.closeStatementList();
        return super.end(elementName);
    }
}
