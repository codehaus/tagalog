/*
 * $Id: SequenceTag.java,v 1.4 2004-12-17 13:43:04 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.jdbc.ProcStatement;
import org.codehaus.tagalog.jdbc.SequenceStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class SequenceTag extends AbstractCompoundStatementTag {
    protected ProcStatement createProcStatement(String elementName,
                                                Attributes attribute)
    {
        return new SequenceStatement();
    }
}
