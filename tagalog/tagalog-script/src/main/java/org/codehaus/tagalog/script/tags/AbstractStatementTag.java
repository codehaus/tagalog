/*
 * $Id: AbstractStatementTag.java,v 1.13 2005-05-17 16:16:23 krisb Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.script.Statement;

/**
 * Shared behaviour for all tags that create {@link Statement} objects.
 * Provides the following behaviour to classes that extend it:
 *
 * <ul>
 * <li>
 * Storage of the reference to the {@link Statement} object in the
 * {@link #stmt} attribute. A value must be assigned to <code>stmt</code>
 * before the {@link #end(String)} method is called.
 * </li>
 * <li>
 * Some helper methods to simplify parsing of expressions.
 * </li>
 * </ul>
 *
 * Subclasses will typically assign a value to the {@link #stmt} attribute
 * in the <code>begin</code> method if all the necessary information is
 * available from the attributes.
 * Otherwise information can be collected during the lifecycle of the tag
 * and the <code>Statement</code> created in the implementation of the
 * {@link #end(String)} method, <strong>before</strong> calling
 * <code>super.end(elementName)</code>.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.13 $
 */
public abstract class AbstractStatementTag
    extends AbstractTag
{
    protected Statement stmt;

    public Object end(String elementName) throws TagException {
        return stmt;
    }

    public boolean recycle() {
        stmt = null;
        return true;
    }

    protected final ParseController getExpressionParser() {
        return ScriptTagUtils.getExpressionParser(getContext());
    }

    protected final Expression parseExpression(Attributes attributes,
                                               String elementName,
                                               String attributeName)
        throws TagException
    {
        String valueText = attributes.getValue(attributeName);
        if (valueText == null)
            return null;
        try {
            return getExpressionParser().parse(valueText);
        } catch (ExpressionParseException e) {
            throw new TagException("could not parse '"
                                   + attributeName + "' attribute of <"
                                   + elementName + ">", e);
        }
    }

    protected final Expression parseRequiredExpression(Attributes attributes,
                                                       String elementName,
                                                       String attributeName)
        throws TagException
    {
        String valueText = requireAttribute(attributes, attributeName);
        try {
            return getExpressionParser().parse(valueText);
        } catch (ExpressionParseException e) {
            throw new TagException("could not parse '"
                                   + attributeName + "' attribute of <"
                                   + elementName + ">", e);
        }
    }
}
