/*
 * $Id: OutTag.java,v 1.2 2004-11-08 07:13:31 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.script.core.Out;
import org.codehaus.tagalog.script.tags.AbstractStatementTag;

/**
 * OutTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class OutTag extends AbstractStatementTag {
    private Expression value;

    private Expression defaultValue;

    public void begin(String elementName, Attributes attribute)
        throws TagException
    {
        String valueText = requireAttribute(attribute, elementName, "value");
        try {
            value = ParseController.DEFAULT.parse(valueText);
        } catch (ExpressionParseException e) {
            throw new TagException("could not parse 'value' attribute", e);
        }

        String defaultText = attribute.getValue("default");
        if (defaultText != null) {
            try {
                defaultValue = ParseController.DEFAULT.parse(defaultText);
            } catch (ExpressionParseException e) {
                throw new TagException("could not parse 'default' attribute",
                                       e);
            }
        }
    }

    public void child(Object child) throws TagException {
        throw new TagException("<out> cannot contain XML elements in its body");
    }

    public void text(char[] characters, int start, int length)
        throws TagException
    {
        if (defaultValue != null)
            throw new TagException("<out> must not have 'default' attribute"
                                   + " and body content");
        String defaultText = new String(characters, start, length);
        try {
            defaultValue = ParseController.DEFAULT.parse(defaultText);
        } catch (ExpressionParseException e) {
            throw new TagException("could not parse 'default' attribute",
                                   e);
        }
    }

    public Object end(String elementName) throws TagException {
        stmt = new Out(value, defaultValue, true);
        return super.end(elementName);
    }

    public boolean recycle() {
        value = null;
        defaultValue = null;
        return super.recycle();
    }
}
