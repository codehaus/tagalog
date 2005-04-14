/*
 * $Id: AbstractStatementTag.java,v 1.7 2005-04-14 14:08:25 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.Tag;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
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
 * Adding the completed <code>stmt</code> to the nearest enclosing
 * compound statement when the {@link #end(String)} method is called.
 * If there is no enclosing compound statement a {@link TagException} is
 * thrown, unless the {@link #rootTag} attribute has been set to true.
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
 * @version $Revision: 1.7 $
 */
public abstract class AbstractStatementTag
    extends AbstractTag
{
    /**
     * Is this tag the root tag of a script hierarchy?
     */
    protected boolean rootTag = false;

    protected Statement stmt;

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        Tag tag;

        if (rootTag)
            tag = findAncestor(AbstractCompoundStatementTag.class);
        else
            tag = requireAncestor("compound statement",
                                  AbstractCompoundStatementTag.class);
        if (tag != null)
            ((AbstractCompoundStatementTag) tag).addStatement(stmt);
        return stmt;
    }

    public boolean recycle() {
        stmt = null;
        return true;
    }

    public Expression parseExpression(Attributes attributes,
                                      String elementName,
                                      String attributeName)
        throws TagException
    {
        String valueText = attributes.getValue(attributeName);
        if (valueText == null)
            return null;
        try {
            return ParseController.DEFAULT.parse(valueText);
        } catch (ExpressionParseException e) {
            throw new TagException("could not parse '"
                                   + attributeName + "' attribute of <"
                                   + elementName + ">", e);
        }
    }

    public Expression parseRequiredExpression(Attributes attributes,
                                              String elementName,
                                              String attributeName)
        throws TagException
    {
        String valueText = requireAttribute(attributes,
                                            elementName, attributeName);
        try {
            return ParseController.DEFAULT.parse(valueText);
        } catch (ExpressionParseException e) {
            throw new TagException("could not parse '"
                                   + attributeName + "' attribute of <"
                                   + elementName + ">", e);
        }
    }
}
