/*
 * $Id: ScriptUtilsTest.java,v 1.2 2005-04-20 16:01:24 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.tagalog.el.ParseController;

import junit.framework.TestCase;

/**
 * Tests for the {@link ScriptUtils} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ScriptUtilsTest extends TestCase {

    public void testExpressionParseController() {
        Map context = new HashMap();

        ParseController p1 = ScriptUtils.expressionParseController(context);

        assertNotNull(p1);
        assertNotNull(context.get(Script.TAGALOG_EL_PARSER));

        ParseController p2 = ScriptUtils.expressionParseController(context);

        assertSame(p1, p2);
        assertSame(p1, context.get(Script.TAGALOG_EL_PARSER));
    }

    private void assertEscaped(String expected, String value) {
        assertEquals(expected, ScriptUtils.escapeXml(value));
    }

    public void testEscapeXml() {
        assertEscaped("123&lt;45&gt;67", "123<45>67");
        assertEscaped("&lt;&gt;&amp;&#039;&#034;", "<>&'\"");
    }
}
