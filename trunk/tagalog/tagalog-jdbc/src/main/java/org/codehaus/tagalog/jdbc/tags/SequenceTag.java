/*
 * $Id: SequenceTag.java,v 1.3 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;

import org.codehaus.tagalog.jdbc.SequenceStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public class SequenceTag extends AbstractCompoundStatementTag {
    public void begin(String elementName, Attributes attribute) {
        stmt = new SequenceStatement();
    }
}
