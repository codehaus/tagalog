/*
 * $Id: SetTag.java,v 1.2 2005-04-07 15:56:15 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.script.core.Set;
import org.codehaus.tagalog.script.tags.AbstractStatementTag;

/**
 * SetTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class SetTag extends AbstractStatementTag {
    private String var;

    private Expression value;

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        var = attributes.getValue("var");

        value = parseExpression(attributes, elementName, "value");
    }

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        throw new TagException("<set> cannot contain XML elements in its body");
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
        if (value != null)
            throw new TagException("<set> must not have 'value' attribute"
                                   + " and body content");
        String defaultText = new String(characters, start, length);
        try {
            value = ParseController.DEFAULT.parse(defaultText);
        } catch (ExpressionParseException e) {
            throw new TagException("could not parse 'default' attribute",
                                   e);
        }
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        stmt = new Set(var, value);
        return super.end(elementName);
    }
}
