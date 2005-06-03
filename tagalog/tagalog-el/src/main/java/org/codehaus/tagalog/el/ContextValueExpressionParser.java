/*
 * $Id: ContextValueExpressionParser.java,v 1.4 2005-06-03 16:13:17 krisb Exp $
 */

package org.codehaus.tagalog.el;

/**
 * ContextValueExpressionParser
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public final class ContextValueExpressionParser extends SimpleExpressionParser {

    private final Object nullValue;

    public ContextValueExpressionParser() {
        this(null);
    }

    public ContextValueExpressionParser(Object nullValue) {
        this.nullValue = nullValue;
    }

    public Expression parse(String text) throws ExpressionParseException {
        text = text.trim();
        return new ContextValueExpression(text, nullValue);
    }
}
