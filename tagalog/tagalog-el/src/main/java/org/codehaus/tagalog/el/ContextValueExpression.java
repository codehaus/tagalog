/*
 * $Id: ContextValueExpression.java,v 1.2 2005-04-19 20:48:12 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;

/**
 * ContextValueExpression
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ContextValueExpression implements Expression {
    private final String contextKey;

    public ContextValueExpression(String key) {
        contextKey = key;
    }

    public Object evaluate(Map context) {
        return context.get(contextKey);
    }
}
