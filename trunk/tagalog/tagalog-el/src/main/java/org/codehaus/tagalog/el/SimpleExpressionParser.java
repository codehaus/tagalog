/*
 * $Id: SimpleExpressionParser.java,v 1.1 2005-04-20 15:57:07 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Abstract class that simplifies the {@link ExpressionParser} interface by
 * discarding the <code>ParseController</code> argument normally required
 * by the {@link ExpressionParser#parse(String, ParseController)} method.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public abstract class SimpleExpressionParser implements ExpressionParser {
    public Expression parse(String text, ParseController parseController)
        throws ExpressionParseException
    {
        return parse(text);
    }

    public abstract Expression parse(String text)
        throws ExpressionParseException;
}
