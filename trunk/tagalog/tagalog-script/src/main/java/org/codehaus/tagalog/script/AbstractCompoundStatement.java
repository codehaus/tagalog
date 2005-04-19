/*
 * $Id: AbstractCompoundStatement.java,v 1.4 2005-04-19 16:32:14 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

import org.codehaus.tagalog.el.Expression;

/**
 * Abstract base class for {@link Statement}s that contain
 * {@link Expression}s as their body. The contained <code>Expression</code>
 * may also be a {@link Statement} wrapped in a {@link StatementExpression}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public abstract class AbstractCompoundStatement
    extends AbstractStatement
    implements Statement
{
    protected final Expression body;

    protected AbstractCompoundStatement(Expression body) {
        this.body = body;
    }

    /**
     * Evalutate the {@link Expression} that is the body of this statement.
     * Any return value from the expression is discarded.
     */
    public void execute(Map context) throws Exception {
        evaluateBody(context);
    }

    /**
     * Evaluate the {@link Expression} that is the body of this statement
     * against a given context, returning the evaluation result.
     *
     * @param context Evaluation context.
     * @return Evaluation result.
     * @throws Exception
     */
    protected final Object evaluateBody(Map context) throws Exception {
        return body.evaluate(context);
    }
}
