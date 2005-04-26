/*
 * $Id: ScriptUtilsTest.java,v 1.3 2005-04-26 15:30:37 mhw Exp $
 */

package org.codehaus.tagalog.script;

import junit.framework.TestCase;

/**
 * Tests for the {@link ScriptUtils} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class ScriptUtilsTest extends TestCase {

    private void assertEscaped(String expected, String value) {
        assertEquals(expected, ScriptUtils.escapeXml(value));
    }

    public void testEscapeXml() {
        assertEscaped("123&lt;45&gt;67", "123<45>67");
        assertEscaped("&lt;&gt;&amp;&#039;&#034;", "<>&'\"");
    }
}
