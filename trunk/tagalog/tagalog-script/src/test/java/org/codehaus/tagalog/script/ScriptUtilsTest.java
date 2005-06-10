/*
 * $Id: ScriptUtilsTest.java,v 1.5 2005-06-10 12:43:21 krisb Exp $
 */

package org.codehaus.tagalog.script;

import junit.framework.TestCase;

/**
 * Tests for the {@link ScriptUtils} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.5 $
 */
public class ScriptUtilsTest extends TestCase {

    private static final int ITERATIONS = 10000;

    public void testEscapeXml() {
        assertEquals("123&lt;45&gt;67",
                ScriptUtils.escapeXml("123<45>67"));
        assertEquals("&lt;&gt;&amp;&#039;&#034;",
                ScriptUtils.escapeXml("<>&'\""));
        assertNull(ScriptUtils.escapeXml(null));
    }

}
