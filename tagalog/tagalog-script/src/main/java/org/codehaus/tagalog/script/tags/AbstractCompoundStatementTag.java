/*
 * $Id: AbstractCompoundStatementTag.java,v 1.10 2005-07-19 08:44:51 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.script.ExpressionStatement;
import org.codehaus.tagalog.script.Statement;
import org.codehaus.tagalog.script.StatementList;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.10 $
 */
public class AbstractCompoundStatementTag
    extends AbstractStatementTag
{
    private StatementList statementList;

    private ExpressionCollector contentExpression;

    private void addBodyContentExpression() throws TagException {
        Expression expression = parseBodyContentExpression();
        if (expression != null)
            statementList.addExpression(expression);
    }

    private Expression parseBodyContentExpression() throws TagException {
        Expression result = contentExpression.parse();
        contentExpression = null;
        return result;
    }

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
        statementList = new StatementList();
    }

    public void text(char[] characters, int start, int length)
        throws TagException
    {
        if (contentExpression == null) {
            ParseController parser = getExpressionParser();
            contentExpression = new ExpressionCollector(parser, length);
        }
        contentExpression.append(characters, start, length);
    }

    public void child(TagBinding childType, Object child) throws TagException {
        if (contentExpression != null)
            addBodyContentExpression();
        if (child instanceof Statement)
            statementList.addStatement((Statement) child);
        else if (child instanceof Expression)
            statementList.addExpression((Expression) child);
    }

    public Object end(String elementName) throws TagException {
        if (contentExpression == null) {
            if (statementList.size() == 0)
                stmt = createStatement((Expression) null);
            else
                stmt = createStatement(statementList);
        } else {
            if (statementList.size() == 0)
                stmt = createStatement(parseBodyContentExpression());
            else {
                addBodyContentExpression();
                stmt = createStatement(statementList);
            }
        }
        return super.end(elementName);
    }

    public boolean recycle() {
        statementList = null;
        contentExpression = null;
        return super.recycle();
    }

    /**
     * Create a {@link Statement} object that encapsulates an expression.
     * If the body of the tag was empty then this method will be passed
     * a <code>null</code> expression.
     *
     * @param body
     * @return
     */
    protected Statement createStatement(Expression body) {
        if (body == null)
            return Statement.NULL;
        return new ExpressionStatement(body);
    }

    /**
     * Create a {@link Statement} object that encapsulates a list of
     * statements. This method will only be called if the body of the
     * tag contained nested elements.
     *
     * @param body The list of statements created from the tag body.
     *             Will not be <code>null</code>, and will always contain
     *             at least one statement.
     * @return Suitable statement for the tag.
     */
    protected Statement createStatement(StatementList body) {
        return body.getStatement();
    }
}
