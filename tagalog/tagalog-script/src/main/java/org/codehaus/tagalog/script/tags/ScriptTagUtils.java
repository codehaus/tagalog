/*
 * $Id: ScriptTagUtils.java,v 1.1 2005-04-26 15:30:37 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import java.util.Map;

import org.codehaus.tagalog.el.ExpressionParser;
import org.codehaus.tagalog.el.ParseController;

/**
 * Miscellaneous static utility methods for script tag implementation.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class ScriptTagUtils {

    /**
     * Private constructor to prevent instantiation
     */
    private ScriptTagUtils() {
    }

    public static ParseController getExpressionParser(Map context) {
        ParseController result;

        result = (ParseController) context.get(ScriptTagLibrary.TAGALOG_EL_PARSER);
        if (result == null) {
            result = (ParseController) ParseController.DEFAULT.clone();
            context.put(ScriptTagLibrary.TAGALOG_EL_PARSER, result);
        }
        return result;
    }

    public static void setExpressionParser(Map context,
                                           ParseController newController)
    {
        if (newController == null)
            throw new NullPointerException("new parser is null");
        context.put(ScriptTagLibrary.TAGALOG_EL_PARSER, newController);
    }

    public static void replaceExpressionParser(Map context, String name,
                                               ExpressionParser parser)
    {
        ParseController controller = getExpressionParser(context);
        controller = (ParseController) controller.clone();
        controller.replaceExpressionLanguage(name, parser);
        setExpressionParser(context, controller);
    }
}
