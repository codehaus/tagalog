/*
 * $Id: JavaTagLibraryResolverTest.java,v 1.1 2004-02-12 01:00:36 mhw Exp $
 */

package org.codehaus.tagalog;

import junit.framework.TestCase;

/**
 * Test the {@link JavaTagLibraryResolver}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class JavaTagLibraryResolverTest extends TestCase {
    public void testJavaTagLibraryResolver() {
        ParserConfiguration p = new ParserConfiguration();
        p.addTagLibraryResolver(new JavaTagLibraryResolver());

        TagLibrary t = p.findTagLibrary("java:org.codehaus.tagalog.MockTagLibrary");
        assertNotNull(t);
        assertTrue(t instanceof MockTagLibrary);
    }
}
