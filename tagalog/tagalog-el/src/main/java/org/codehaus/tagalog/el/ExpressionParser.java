/*
 * $Id: ExpressionParser.java,v 1.1 2004-10-26 19:14:34 mhw Exp $
 */

package org.codehaus.tagalog.el;

/**
 * ExpressionParser
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface ExpressionParser {
    Expression parse(String text) throws ExpressionParseException;
}
