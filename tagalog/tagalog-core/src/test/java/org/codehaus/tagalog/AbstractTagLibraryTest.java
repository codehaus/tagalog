/*
 * $Id: AbstractTagLibraryTest.java,v 1.6 2004-11-17 17:41:31 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

/**
 * Tests for {@link AbstractTagLibrary}, using the {@link MockTagLibrary}
 * subclass.
 * <p>
 * Note that these tests fail if the source has been compiled via eclipse.  See
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=77473 for further details.
 * 
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.6 $
 */
public class AbstractTagLibraryTest extends TestCase {

    public void testRegisterTag() throws Exception {
        TagLibrary tagLibrary;
        Tag tag;

        try {
            tagLibrary = new MockTagLibrary(null, MockTag.class);
            fail("registered tag for null tag name");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            tagLibrary = new MockTagLibrary("", MockTag.class);
            fail("registered tag for empty tag name");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            tagLibrary = new MockTagLibrary("fred", null);
            fail("registered null tag");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            tagLibrary = new MockTagLibrary("fred", String.class);
            fail("registered tag that doesn't implement Tag interface");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            tagLibrary = new MockTagLibrary("fred",
                                        InterfaceThatImplementsTag.class);
            fail("registered tag that is interface");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            tagLibrary = new MockTagLibrary("fred",
                                        AbstractClassThatImplementsTag.class);
            fail("registered tag that is abstract");
        } catch (IllegalArgumentException e) {
            // expected
        }

        try {
            tagLibrary = new MockTagLibrary("fred",
                                        PrivateClassThatImplementsTag.class);
            fail("registered tag that is private");
        } catch (IllegalArgumentException e) {
            // expected
        }

        tagLibrary = new MockTagLibrary("fred", MockTag.class);
        tag = tagLibrary.getTag("fred");
        assertTrue(tag instanceof MockTag);
    }

    private static final class TestTagLibrary extends AbstractTagLibrary {
        public static final String NS_URI = "tagalog:test";

        public TestTagLibrary() {
            registerTag("foo", MockTag.class);
            registerTag("bar", MockRodTag.class);
            registerTag("baz", MockRodTag.class);
        }
    }

    public void testGetTag() {
        TagLibrary tagLibrary = new TestTagLibrary();
        Tag tag1, tag2, tag3;

        tag1 = tagLibrary.getTag("foo");
        assertTrue(tag1 instanceof MockTag);
        tag2 = tagLibrary.getTag("foo");
        assertTrue(tag2 instanceof MockTag);
        assertNotSame(tag1, tag2);
        tag3 = tagLibrary.getTag("foo");
        assertTrue(tag3 instanceof MockTag);
        assertNotSame(tag1, tag3);
        assertNotSame(tag2, tag3);

        tag1 = tagLibrary.getTag("bar");
        assertTrue(tag1 instanceof MockRodTag);
        tag2 = tagLibrary.getTag("baz");
        assertTrue(tag2 instanceof MockRodTag);
        assertNotSame(tag1, tag2);
        tag3 = tagLibrary.getTag("bar");
        assertTrue(tag3 instanceof MockRodTag);
        assertNotSame(tag1, tag3);
        assertNotSame(tag2, tag3);

        assertNull(tagLibrary.getTag("does-not-exist"));

        try {
            tagLibrary.getTag(null);
            fail("got tag with null tag name");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            tagLibrary.getTag("");
            fail("got tag with empty tag name");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testReleaseTag() {
        AbstractTagLibrary tagLibrary = new TestTagLibrary();
        Set tags = new HashSet();

        for (int i = 0; i < 5; i++) {
            tags.add(tagLibrary.getTag("foo"));
        }
        assertEquals(5, tags.size());
        assertEquals("foo: 5", tagLibrary.listUnreleasedTags());

        Iterator iter = tags.iterator();
        while (iter.hasNext()) {
            Tag tag = (Tag) iter.next();
            tagLibrary.releaseTag("foo", tag);
        }
        assertEquals("", tagLibrary.listUnreleasedTags());

        for (int i = 0; i < 5; i++) {
            Tag tag = tagLibrary.getTag("foo");
            assertTrue(tags.remove(tag));
        }
        assertEquals(0, tags.size());
        assertEquals("foo: 5", tagLibrary.listUnreleasedTags());
    }

    public void testTagRecycling() {
        ParserConfiguration p = new ParserConfiguration();
        AbstractTagLibrary tagLibrary;
        Tag tag1, tag2;

        p.addTagLibrary("recycle", new MockTagLibrary("tag", MockTag.class));
        p.addTagLibrary("discard", new MockTagLibrary("tag", MockRodTag.class));

        tagLibrary = (AbstractTagLibrary) p.findTagLibrary("recycle");
        tag1 = tagLibrary.getTag("tag");
        assertNotNull(tag1);
        tagLibrary.releaseTag("tag", tag1);
        tag2 = tagLibrary.getTag("tag");
        assertSame(tag1, tag2);
        tagLibrary.releaseTag("tag", tag2);
        assertEquals("", tagLibrary.listUnreleasedTags());

        tagLibrary = (AbstractTagLibrary) p.findTagLibrary("discard");
        tag1 = tagLibrary.getTag("tag");
        assertNotNull(tag1);
        tagLibrary.releaseTag("tag", tag1);
        tag2 = tagLibrary.getTag("tag");
        assertNotSame(tag1, tag2);
        tagLibrary.releaseTag("tag", tag2);
        assertEquals("", tagLibrary.listUnreleasedTags());
    }

    //
    // Some implementations of Tag that cannot be instantiated
    //

    public interface InterfaceThatImplementsTag extends Tag {
    }

    public abstract static class AbstractClassThatImplementsTag implements Tag {
    }

    private static class PrivateClassThatImplementsTag extends AbstractTag {
    }
}
