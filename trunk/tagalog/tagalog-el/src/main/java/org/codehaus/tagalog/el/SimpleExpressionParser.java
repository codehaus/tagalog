/*
 * $Id: SimpleExpressionParser.java,v 1.2 2005-06-01 07:07:02 krisb Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Abstract class that simplifies the {@link ExpressionParser} interface by
 * discarding the <code>ParseController</code> argument normally required
 * by the {@link ExpressionParser#parse(String, ParseController)} method.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public abstract class SimpleExpressionParser implements ExpressionParser {
    public final Expression parse(String text, ParseController parseController)
        throws ExpressionParseException
    {
        return parse(text);
    }

    public abstract Expression parse(String text)
        throws ExpressionParseException;
}
