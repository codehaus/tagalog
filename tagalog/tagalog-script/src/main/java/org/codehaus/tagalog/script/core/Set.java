/*
 * $Id: Set.java,v 1.4 2005-04-26 17:04:43 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

import java.util.Map;

import org.codehaus.tagalog.conv.PropertySetter;
import org.codehaus.tagalog.el.Expression;
import org.codehaus.tagalog.script.AbstractCompoundStatement;
import org.codehaus.tagalog.script.ScriptException;
import org.codehaus.tagalog.script.Statement;

/**
 * Implementation of the <code>set</code> JSTL action.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public final class Set
    extends AbstractCompoundStatement
    implements Statement
{
    private final String var;

    private final Expression targetExpression;

    private final Expression propertyNameExpression;

    public Set(String var, Expression value) {
        super(value);
        if (var == null)
            throw new NullPointerException("null var");
        this.var = var;
        this.targetExpression = null;
        this.propertyNameExpression = null;
    }

    public Set(Expression targetExpression, Expression propertyNameExpression,
               Expression value)
    {
        super(value);
        this.var = null;
        if (targetExpression == null)
            throw new NullPointerException("null target expression");
        this.targetExpression = targetExpression;
        if (propertyNameExpression == null)
            throw new NullPointerException("null property name expression");
        this.propertyNameExpression = propertyNameExpression;
    }

    public void execute(Map context) throws ScriptException {
        Object result = evaluateBody(context);
        Object target = null;
        Map map = null;
        String property = null;

        if (var == null) {
            target = evaluateNotNull("target", targetExpression, context);
            if (target instanceof Map)
                map = (Map) target;
            property = evaluateNotNull("property", propertyNameExpression,
                                       context).toString();
        } else {
            map = context;
        }

        if (map != null) {
            if (result != null)
                map.put(var, result);
            else
                map.remove(var);
        } else {
            if (result instanceof String) {
                PropertySetter setter = new PropertySetter(target.getClass());

                try {
                    setter.setProperty(target, property, (String) result);
                } catch (Exception e) {
                    throw new ScriptException("property set failed", e);
                }
            } else {
                throw new ScriptException("coercion from " + result.getClass()
                                          + " not supported");
            }
        }
    }
}
