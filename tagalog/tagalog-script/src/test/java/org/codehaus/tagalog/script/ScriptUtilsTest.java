/*
 * $Id: ScriptUtilsTest.java,v 1.4 2005-06-07 16:36:04 krisb Exp $
 */

package org.codehaus.tagalog.script;

import junit.framework.TestCase;

/**
 * Tests for the {@link ScriptUtils} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public class ScriptUtilsTest extends TestCase {

    private static final int ITERATIONS = 10000;

    public void testEscapeXmlNew() {
        assertEquals("123&lt;45&gt;67",
                ScriptUtils.escapeXml("123<45>67"));
        assertEquals("&lt;&gt;&amp;&#039;&#034;",
                ScriptUtils.escapeXml("<>&'\""));
        assertNull(ScriptUtils.escapeXml(null));
    }

    public void testSpeed() throws Exception {
        long start, end, dur;
        String test = "<>&'\"<>&'\"";
        String expected = "&lt;&gt;&amp;&#039;&#034;&lt;&gt;&amp;&#039;&#034;";

        start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            assertEquals(expected, escapeXmlOld(test));
        }
        end = System.currentTimeMillis();
        dur = end - start;
        System.out.println("old: " + dur + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            assertEquals(expected, ScriptUtils.escapeXml(test));
        }
        end = System.currentTimeMillis();
        dur = end - start;
        System.out.println("new: " + dur + " ms");
    }

    // --- Old Implementation

    private static String[][] xmlReplacements = {
            { "&", "&amp;" },
            { "<", "&lt;" },
            { ">", "&gt;" },
            { "'", "&#039;" },
            { "\"", "&#034;" },
        };

    /**
     * Replace characters that have significance in XML with their
     * escaped equivalents, as per the JSTL specification.
     *
     * @param s String to be processed.
     * @return Processed string.
     */
    private static String escapeXmlOld(String s) {
        for (int i = 0; i < xmlReplacements.length; i++) {
            s = s.replaceAll(xmlReplacements[i][0], xmlReplacements[i][1]);
        }
        return s;
    }

    public void testEscapeXmlOld() {
        assertEquals("123&lt;45&gt;67",
                escapeXmlOld("123<45>67"));
        assertEquals("&lt;&gt;&amp;&#039;&#034;",
                escapeXmlOld("<>&'\""));
    }


}
