/*
 * $Id: OutTag.java,v 1.6 2005-04-19 16:37:33 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.script.Statement;
import org.codehaus.tagalog.script.StatementList;
import org.codehaus.tagalog.script.core.Out;
import org.codehaus.tagalog.script.tags.AbstractCompoundStatementTag;

/**
 * OutTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.6 $
 */
public class OutTag extends AbstractCompoundStatementTag {
    private Expression value;

    private Expression defaultValue;

    private boolean escapeXml;

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        super.begin(elementName, attributes);
        value = parseRequiredExpression(attributes, elementName, "value");
        defaultValue = parseExpression(attributes, elementName, "default");
        escapeXml = booleanAttribute(attributes, elementName, "escapeXml", true);
    }

    private boolean booleanAttribute(Attributes attributes,
                                     String elementName, String attributeName,
                                     boolean defaultValue)
        throws TagException
    {
        String value = attributes.getValue(attributeName);

        if (value == null)
            return defaultValue;
        else if (value.equals("true"))
            return true;
        else if (value.equals("false"))
            return false;
        else
            throw new TagException("'" + attributeName + "' must be either"
                                   + " true or false for"
                                   + " <" + elementName + ">");
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
        if (defaultValue != null)
            throw new TagException("<out> must not have 'default' attribute"
                                   + " and body content");
        super.text(characters, start, length);
    }

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        if (defaultValue != null)
            throw new TagException("<out> must not have 'default' attribute"
                                   + " and body content");
        super.child(childType, child);
    }

    protected Statement createStatement(Expression body) {
        if (body == null)
            return new Out(value, defaultValue, escapeXml);
        return new Out(value, body, escapeXml);
    }

    protected Statement createStatement(StatementList body) {
        return new Out(value, body.getStatementExpression(), escapeXml);
    }

    public boolean recycle() {
        value = null;
        defaultValue = null;
        return super.recycle();
    }
}
