/*
 * $Id: DialectChoiceTag.java,v 1.3 2004-10-01 15:02:22 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;

import org.codehaus.tagalog.jdbc.DialectChoiceStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public class DialectChoiceTag extends AbstractCompoundStatementTag {
    public void begin(String elementName, Attributes attributes) {
        String s;

        stmt = new DialectChoiceStatement();
        s = attributes.getValue("optional");
        if (s != null && s.equals("true"))
            ((DialectChoiceStatement) stmt).setOptional(true);
    }
}
