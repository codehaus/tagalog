/*
 * $Id: AbstractCompoundStatementTag.java,v 1.3 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.CompoundProcStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public class AbstractCompoundStatementTag extends AbstractProcStatementTag {
    public Object end(String elementName) throws TagException {
        ((CompoundProcStatement) stmt).closeStatementList();
        return super.end(elementName);
    }
}
