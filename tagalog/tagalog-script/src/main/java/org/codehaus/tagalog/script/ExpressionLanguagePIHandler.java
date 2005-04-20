/*
 * $Id: ExpressionLanguagePIHandler.java,v 1.1 2005-04-20 16:01:09 mhw Exp $
 */

package org.codehaus.tagalog.script;
import java.util.Map;

import org.codehaus.tagalog.el.ContextValueExpressionParser;
import org.codehaus.tagalog.el.ExpressionParser;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.el.ognl.OgnlExpressionParser;
import org.codehaus.tagalog.pi.PIHandler;

/**
 * Handler for XML processing instructions that switches the standard
 * expression parser.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ExpressionLanguagePIHandler implements PIHandler {

    public static final String EL_PI_TARGET = "script-el";

    public void processingInstruction(String target, String data, Map context) {
        if (!target.equals(EL_PI_TARGET))
            return;
        data = data.trim();

        ExpressionParser parser = null;

        if (data.equals("context-value"))
            parser = new ContextValueExpressionParser();
        else if (data.equals("ognl"))
            parser = new OgnlExpressionParser();

        if (parser != null) {
            ParseController controller;

            controller = ScriptUtils.expressionParseController(context);
            controller.replaceExpressionLanguage(ParseController.STANDARD,
                                                 parser);
        }
    }
}
