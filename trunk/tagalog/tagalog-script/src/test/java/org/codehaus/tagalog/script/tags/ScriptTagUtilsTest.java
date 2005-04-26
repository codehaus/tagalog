/*
 * $Id: ScriptTagUtilsTest.java,v 1.1 2005-04-26 15:30:37 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.codehaus.tagalog.el.ParseController;

/**
 * Tests for the {@link ScriptTagUtils} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ScriptTagUtilsTest extends TestCase {

    public void testExpressionParseController() {
        Map context = new HashMap();

        ParseController p1 = ScriptTagUtils.getExpressionParser(context);

        assertNotNull(p1);
        assertNotNull(context.get(ScriptTagLibrary.TAGALOG_EL_PARSER));

        ParseController p2 = ScriptTagUtils.getExpressionParser(context);

        assertSame(p1, p2);
        assertSame(p1, context.get(ScriptTagLibrary.TAGALOG_EL_PARSER));
    }
}
