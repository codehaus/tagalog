/*
 * $Id: ScriptUtilsTest.java,v 1.6 2005-06-23 13:10:32 mhw Exp $
 */

package org.codehaus.tagalog.script;

import junit.framework.TestCase;

/**
 * Tests for the {@link ScriptUtils} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.6 $
 */
public class ScriptUtilsTest extends TestCase {

    public void testEscapeXml() {
        assertEquals("123&lt;45&gt;67",
                ScriptUtils.escapeXml("123<45>67"));
        assertEquals("&lt;&gt;&amp;&#039;&#034;",
                ScriptUtils.escapeXml("<>&'\""));
        assertNull(ScriptUtils.escapeXml(null));
    }

}
