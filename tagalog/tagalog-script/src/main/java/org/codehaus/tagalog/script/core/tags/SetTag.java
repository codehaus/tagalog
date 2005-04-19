/*
 * $Id: SetTag.java,v 1.3 2005-04-19 16:37:33 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.script.Statement;
import org.codehaus.tagalog.script.StatementList;
import org.codehaus.tagalog.script.core.Set;
import org.codehaus.tagalog.script.tags.AbstractCompoundStatementTag;

/**
 * SetTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class SetTag extends AbstractCompoundStatementTag {
    private String var;

    private Expression value;

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        super.begin(elementName, attributes);
        var = attributes.getValue("var");
        value = parseExpression(attributes, elementName, "value");
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
        if (value != null)
            throw new TagException("<set> must not have 'value' attribute"
                                   + " and body content");
        super.text(characters, start, length);
    }

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        if (value != null)
            throw new TagException("<set> must not have 'value' attribute"
                                   + " and body content");
        super.child(childType, child);
    }

    protected Statement createStatement(Expression body) {
        if (body == null)
            return new Set(var, value);
        return new Set(var, body);
    }

    protected Statement createStatement(StatementList body) {
        return new Set(var, body.getStatementExpression());
    }

    public boolean recycle() {
        var = null;
        value = null;
        return super.recycle();
    }
}
