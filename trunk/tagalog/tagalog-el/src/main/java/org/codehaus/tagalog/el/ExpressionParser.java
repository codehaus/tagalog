/*
 * $Id: ExpressionParser.java,v 1.2 2005-04-20 15:57:07 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * Interface implemented by expression parsers.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface ExpressionParser {

    /**
     * Parse text into an expression, handing off sub-expressions through
     * the supplied <code>ParseController</code>.
     *
     * @param text The text to parse
     * @param parseController
     * @return
     * @throws ExpressionParseException
     */
    Expression parse(String text, ParseController parseController)
        throws ExpressionParseException;
}
