/*
 * $Id: ExpressionCollector.java,v 1.1 2005-04-26 15:32:46 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.el.ExpressionParseException;
import org.codehaus.tagalog.el.ParseController;

/**
 * Group together the text of an expression and the {@link ParseController}
 * that is going to parse it.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class ExpressionCollector {
    private final StringBuffer expressionText;

    private final ParseController parseController;

    public ExpressionCollector(ParseController parseController)
    {
        if (parseController == null)
            throw new NullPointerException("null parseController");
        this.parseController = parseController;
        this.expressionText = new StringBuffer();
    }

    public ExpressionCollector(ParseController parseController, int length) {
        if (parseController == null)
            throw new NullPointerException("null parseController");
        this.parseController = parseController;
        this.expressionText = new StringBuffer(length);
    }

    public Expression parse() throws TagException {
        String text = expressionText.toString();

        if (text.trim().length() == 0)
            return null;
        try {
            return parseController.parse(text);
        } catch (ExpressionParseException e) {
            throw new TagException("failed to parse expression"
                                   + " '" + text + "'", e);
        }
    }

    //
    // StringBuffer delegate methods.
    //

    public StringBuffer append(boolean b) {
        return expressionText.append(b);
    }
    public StringBuffer append(char c) {
        return expressionText.append(c);
    }
    public StringBuffer append(char[] str) {
        return expressionText.append(str);
    }
    public StringBuffer append(char[] str, int offset, int len) {
        return expressionText.append(str, offset, len);
    }
    public StringBuffer append(double d) {
        return expressionText.append(d);
    }
    public StringBuffer append(float f) {
        return expressionText.append(f);
    }
    public StringBuffer append(int i) {
        return expressionText.append(i);
    }
    public StringBuffer append(Object obj) {
        return expressionText.append(obj);
    }
    public StringBuffer append(String str) {
        return expressionText.append(str);
    }
    public StringBuffer append(StringBuffer sb) {
        return expressionText.append(sb);
    }
    public StringBuffer append(long l) {
        return expressionText.append(l);
    }
}
