/*
 * $Id: AbstractCompoundStatementTag.java,v 1.3 2004-11-08 12:35:48 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.script.ExpressionStatement;
import org.codehaus.tagalog.script.Sequence;
import org.codehaus.tagalog.script.Statement;
import org.codehaus.tagalog.script.StatementList;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public class AbstractCompoundStatementTag
    extends AbstractStatementTag
{
    private StatementList statementList;

    protected void addStatement(Statement statement) {
        statementList.addStatement(statement);
    }

    protected Statement[] getStatementList() {
        return statementList.getStatementList();
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
        statementList = new StatementList();
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
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
        addStatement(expressionStatement);
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        stmt = createCompoundStatement(statementList);
        return super.end(elementName);
    }

    protected Statement createCompoundStatement(StatementList body) {
        return new Sequence(body);
    }
}
