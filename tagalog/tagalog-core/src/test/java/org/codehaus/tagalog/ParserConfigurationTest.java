/*
 * $Id: ParserConfigurationTest.java,v 1.5 2005-04-05 16:48:27 mhw Exp $
 */

package org.codehaus.tagalog;

import junit.framework.TestCase;

/**
 * Tests for the {@link ParserConfiguration} class.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.5 $
 */
public class ParserConfigurationTest extends TestCase {
    /*
     * Test for default constructor.
     */
    public void testDefaultConstructor() {
        ParserConfiguration p = new ParserConfiguration();
        TagLibrary tagLibrary = new MockTagLibrary();
        p.addTagLibrary("some-uri", tagLibrary);
        assertSame(tagLibrary, p.findTagLibrary("some-uri"));
    }

    public void testAddTagLibrary() {
        ParserConfiguration p = new ParserConfiguration();

        try {
            p.addTagLibrary(null, new MockTagLibrary());
            fail("added tag library with null URI");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            p.addTagLibrary("", new MockTagLibrary());
            fail("added tag library with empty URI");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            p.addTagLibrary("some-uri", null);
            fail("added null tag library");
        } catch (NullPointerException e) {
            // expected
        }

        TagLibrary t1 = new MockTagLibrary();
        TagLibrary t2 = new MockTagLibrary();

        assertNull(p.findTagLibrary("some-uri"));
        p.addTagLibrary("some-uri", t1);
        assertEquals(t1, p.findTagLibrary("some-uri"));
        p.addTagLibrary("some-uri", t2);
        assertEquals(t2, p.findTagLibrary("some-uri"));

        TagLibrary t3 = new MockTagLibrary();

        assertNull(p.findTagLibrary("some-other-uri"));
        p.addTagLibrary("some-other-uri", t3);
        assertEquals(t2, p.findTagLibrary("some-uri"));
        assertEquals(t3, p.findTagLibrary("some-other-uri"));
        assertNull(p.findTagLibrary("unregistered"));
    }

    public void testAddTagLibraryResolver() {
        ParserConfiguration p = new ParserConfiguration();

        try {
            p.addTagLibraryResolver(new MockTagLibraryResolver(null, null));
            fail("added tag library resolver with null URI prefix");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            p.addTagLibraryResolver(new MockTagLibraryResolver("", null));
            fail("added tag library resolver with empty URI prefix");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            p.addTagLibraryResolver(null);
            fail("added null tag library resolver");
        } catch (NullPointerException e) {
            // expected
        }

        TagLibrary t1 = new MockTagLibrary();
        TagLibrary t2 = new MockTagLibrary();
        TagLibraryResolver r1 = new MockTagLibraryResolver("prefix", t1);
        TagLibraryResolver r2 = new MockTagLibraryResolver("prefix", t2);

        assertNull(p.findTagLibrary("prefix:suffix"));
        p.addTagLibraryResolver(r1);
        assertEquals(t1, p.findTagLibrary("prefix:suffix"));
        p.addTagLibraryResolver(r2);
        assertEquals(t2, p.findTagLibrary("prefix:suffix"));

        TagLibrary t3 = new MockTagLibrary();
        TagLibraryResolver r3 = new MockTagLibraryResolver("jelly", t3);

        assertNull(p.findTagLibrary("jelly:core"));
        p.addTagLibraryResolver(r3);
        assertEquals(t2, p.findTagLibrary("prefix:suffix"));
        assertEquals(t3, p.findTagLibrary("jelly:core"));
        assertNull(p.findTagLibrary("unregistered"));
    }

    public void testSetDefaultNamespace() {
        ParserConfiguration p = new ParserConfiguration();

        assertEquals("", p.getDefaultNamespace());

        p.setDefaultNamespace("some-namespace");
        assertEquals("some-namespace", p.getDefaultNamespace());

        try {
            p.setDefaultNamespace(null);
            fail("set default namespace to null");
        } catch (NullPointerException e) {
            // expected
        }
        assertEquals("some-namespace", p.getDefaultNamespace());

        p.setDefaultNamespace("");
        assertEquals("", p.getDefaultNamespace());
    }

    public void testFindTagLibrary() {
        ParserConfiguration p = new ParserConfiguration();
        TagLibrary t1 = new MockTagLibrary();

        p.addTagLibrary("some-uri", t1);
        assertEquals(t1, p.findTagLibrary("some-uri"));
        assertNull(p.findTagLibrary("unregistered"));

        try {
            p.findTagLibrary(null);
            fail("findTagLibrary(null) returned");
        } catch (NullPointerException e) {
            // expected
        }

        assertNull(p.findTagLibrary(""));
        p.setDefaultNamespace("some-uri");
        assertEquals(t1, p.findTagLibrary(""));
    }
}
