/*
 * $Id: DialectChoiceTag.java,v 1.4 2004-12-17 13:43:04 mhw Exp $
 */

package org.codehaus.tagalog.jdbc.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

import org.codehaus.tagalog.jdbc.DialectChoiceStatement;
import org.codehaus.tagalog.jdbc.ProcStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public class DialectChoiceTag extends AbstractCompoundStatementTag {
    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        super.begin(elementName, attributes);

        String s = attributes.getValue("optional");
        if (s != null && s.equals("true")) {
            DialectChoiceStatement statement = (DialectChoiceStatement) getStatement();
            statement.setOptional(true);
        }
    }

    protected ProcStatement createProcStatement(String elementName,
                                                Attributes attributes)
        throws TagException
    {
        if (attributes.getValue("dialect") != null)
            throw new TagException("<dialect-choice> does not support"
                                   + " the 'dialect' attribute");
        return new DialectChoiceStatement();
    }
}
