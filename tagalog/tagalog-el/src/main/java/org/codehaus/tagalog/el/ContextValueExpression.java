/*
 * $Id: ContextValueExpression.java,v 1.1 2004-10-26 19:14:34 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

/**
 * ContextValueExpression
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ContextValueExpression implements Expression {
    private final String contextKey;

    public ContextValueExpression(String key) {
        contextKey = key;
    }

    public Object evaluate(Map context) {
        Object value = context.get(contextKey);
        if (value == null)
            value = "";
        return value;
    }
}
