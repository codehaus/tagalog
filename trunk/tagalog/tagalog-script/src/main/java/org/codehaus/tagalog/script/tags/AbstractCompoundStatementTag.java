/*
 * $Id: AbstractCompoundStatementTag.java,v 1.1 2004-11-04 18:04:57 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.script.CompoundStatement;
import org.codehaus.tagalog.script.ExpressionStatement;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class AbstractCompoundStatementTag
    extends AbstractStatementTag
{
    public void text(char[] characters, int start, int length)
        throws TagException
    {
        String text;
        Expression expression;
        ExpressionStatement expressionStatement;

        text = new String(characters, start, length);
        try {
            expression = ParseController.DEFAULT.parse(text);
        } catch (ExpressionParseException e) {
            throw new TagException("failed to parse expression '" + text + "'",
                                   e);
        }
        expressionStatement = new ExpressionStatement(expression);
        ((CompoundStatement) stmt).addStatement(expressionStatement);
    }

    public Object end(String elementName) throws TagException {
        ((CompoundStatement) stmt).closeStatementList();
        return super.end(elementName);
    }
}
