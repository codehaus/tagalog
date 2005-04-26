/*
 * $Id: ExpressionLanguagePIHandler.java,v 1.1 2005-04-26 15:30:37 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;


import org.codehaus.tagalog.AbstractNodeHandler;
import org.codehaus.tagalog.PIHandler;
import org.codehaus.tagalog.el.ContextValueExpressionParser;
import org.codehaus.tagalog.el.ExpressionParser;
import org.codehaus.tagalog.el.ParseController;
import org.codehaus.tagalog.el.ognl.OgnlExpressionParser;

/**
 * Handler for XML processing instructions that switches the standard
 * expression parser.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ExpressionLanguagePIHandler
    extends AbstractNodeHandler
    implements PIHandler
{
    public void processingInstruction(String target, String data) {
        ExpressionParser parser = null;

        data = data.trim();

        if (data.equals("context-value"))
            parser = new ContextValueExpressionParser();
        else if (data.equals("ognl"))
            parser = new OgnlExpressionParser();

        if (parser != null)
            ScriptTagUtils.replaceExpressionParser(getContext(),
                                                   ParseController.STANDARD,
                                                   parser);
    }
}
