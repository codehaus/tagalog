/*
 * $Id: TextScannerTest.java,v 1.1 2005-05-26 21:43:38 mhw Exp $
 */

package org.codehaus.tagalog;

import junit.framework.TestCase;

/**
 * Tests for the {@link TextScanner} class that supports {@link TagUtils}
 * smart trimming functionality.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class TextScannerTest extends TestCase {

    public void testCharCategory() {
        assertEquals(TextScanner.NORM, TextScanner.category('a'));
        assertEquals(TextScanner.NORM, TextScanner.category('\07'));

        assertEquals(TextScanner.WS, TextScanner.category('\t'));
        assertEquals(TextScanner.NL, TextScanner.category('\n'));
        assertEquals(TextScanner.NL, TextScanner.category('\r'));
        assertEquals(TextScanner.WS, TextScanner.category(' '));
    }
}
