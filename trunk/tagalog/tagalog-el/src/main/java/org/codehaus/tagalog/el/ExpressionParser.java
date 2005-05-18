/*
 * $Id: ExpressionParser.java,v 1.3 2005-05-18 11:44:49 krisb Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Interface implemented by expression parsers.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public interface ExpressionParser {

    /**
     * Parse text into an expression, handing off sub-expressions through
     * the supplied <code>ParseController</code>.
     *
     * @param text The text to parse
     * @param parseController
     * @return the parsed expression
     * @throws ExpressionParseException
     */
    Expression parse(String text, ParseController parseController)
        throws ExpressionParseException;
}
