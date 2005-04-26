/*
 * $Id: SetTag.java,v 1.5 2005-04-26 16:41:25 mhw Exp $
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
 * @version $Revision: 1.5 $
 */
public class SetTag extends AbstractCompoundStatementTag {
    private String var;

    private Expression target;

    private Expression property;

    private Expression value;

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        super.begin(elementName, attributes);

        // check for 'target' first so we report 'var' as being the
        // missing required attribute (we're expecting 'var' to be the
        // more common usage).

        target = parseExpression(attributes, elementName, "target");
        if (target == null)
            var = requireAttribute(attributes, elementName, "var");
        else
            property = parseRequiredExpression(attributes, elementName,
                                               "property");
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
        if (body == null) {
            if (value != null) {
                body = value;
            } else {
                // an empty body is evaluated to the empty string, not null
                body = Expression.EMPTY_STRING;
            }
        }
        if (var != null)
            return new Set(var, body);
        return new Set(target, property, body);
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
