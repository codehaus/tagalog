/*
 * $Id: ContextValueExpression.java,v 1.3 2005-06-03 16:13:17 krisb Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

/**
 * ContextValueExpression
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ContextValueExpression implements Expression {

    private final String contextKey;
    private final Object nullValue;

    public ContextValueExpression(String key, Object nullValue) {
        contextKey = key;
        this.nullValue = nullValue;
    }

    public Object evaluate(Map context) {
        Object value = context.get(contextKey);
        if (value == null)
            return nullValue;
        return value;
    }
}
