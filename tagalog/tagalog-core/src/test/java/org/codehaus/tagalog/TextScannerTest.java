/*
 * $Id: TextScannerTest.java,v 1.2 2005-06-09 19:54:02 mhw Exp $
 */

package org.codehaus.tagalog;

import junit.framework.TestCase;

/**
 * Tests for the {@link TextScanner} class that supports {@link TagUtils}
 * smart trimming functionality.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class TextScannerTest extends TestCase {

    public void testCharCategory() {
        assertEquals(TextScanner.NORM, TextScanner.category('a'));
        assertEquals(TextScanner.NORM, TextScanner.category('\07'));

        assertEquals(TextScanner.WS, TextScanner.category('\t'));
        assertEquals(TextScanner.NL, TextScanner.category('\n'));
        assertEquals(TextScanner.NORM, TextScanner.category('\r'));
        assertEquals(TextScanner.WS, TextScanner.category(' '));
    }
}
