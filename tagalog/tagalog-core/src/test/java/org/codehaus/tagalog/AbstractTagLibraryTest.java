/*
 * $Id: AbstractTagLibraryTest.java,v 1.10 2005-04-26 14:33:00 mhw Exp $
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
 * @version $Revision: 1.10 $
 */
public class AbstractTagLibraryTest extends TestCase {

    public void testRegisterTag() throws Exception {
        TagLibrary tagLibrary;
        NodeHandler tag;

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
        tag = tagLibrary.getNodeHandler("fred");
        assertTrue(tag instanceof MockTag);
    }

    private static final class TestTagLibrary extends AbstractTagLibrary {
        public static final String NS_URI = "tagalog:test";

        public static final TagBinding FOO = new TagBinding("foo",
                                                            MockTag.class);
        public static final TagBinding BAR = new TagBinding("bar",
                                                            MockRodTag.class);
        public static final TagBinding BAZ = new TagBinding("baz",
                                                            MockRodTag.class);
        public TestTagLibrary() {
            registerTagBinding(FOO);
            registerTagBinding(BAR);
            registerTagBinding(BAZ);
        }
    }

    public void testGetTag() {
        TagLibrary tagLibrary = new TestTagLibrary();
        NodeHandler tag1, tag2, tag3;

        tag1 = tagLibrary.getNodeHandler("foo");
        assertTrue(tag1 instanceof MockTag);
        tag2 = tagLibrary.getNodeHandler("foo");
        assertTrue(tag2 instanceof MockTag);
        assertNotSame(tag1, tag2);
        tag3 = tagLibrary.getNodeHandler("foo");
        assertTrue(tag3 instanceof MockTag);
        assertNotSame(tag1, tag3);
        assertNotSame(tag2, tag3);

        tag1 = tagLibrary.getNodeHandler("bar");
        assertTrue(tag1 instanceof MockRodTag);
        tag2 = tagLibrary.getNodeHandler("baz");
        assertTrue(tag2 instanceof MockRodTag);
        assertNotSame(tag1, tag2);
        tag3 = tagLibrary.getNodeHandler("bar");
        assertTrue(tag3 instanceof MockRodTag);
        assertNotSame(tag1, tag3);
        assertNotSame(tag2, tag3);

        assertNull(tagLibrary.getNodeHandler("does-not-exist"));

        try {
            tagLibrary.getNodeHandler(null);
            fail("got tag with null tag name");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            tagLibrary.getNodeHandler("");
            fail("got tag with empty tag name");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    private static final int SIZE = 30;

    /**
     * Test that releasing tags does the right thing, both for tags that
     * can be recycled and for those that cannot.
     */
    public void testReleaseTag() {
        AbstractTagLibrary tagLibrary = new TestTagLibrary();
        Set tags = new HashSet();

        // get tag instances
        for (int i = 0; i < SIZE; i++) {
            tags.add(tagLibrary.getNodeHandler("foo"));
        }
        assertEquals(SIZE, tags.size());
        assertEquals("foo: " + SIZE, tagLibrary.listUnreleasedTags());

        // release tag instances
        Iterator iter = tags.iterator();
        while (iter.hasNext()) {
            NodeHandler tag = (NodeHandler) iter.next();
            tagLibrary.releaseNodeHandler("foo", tag);
        }
        assertEquals("", tagLibrary.listUnreleasedTags());

        // get tags again, checking them off against our list to make sure
        // they've been recycled
        for (int i = 0; i < SIZE; i++) {
            NodeHandler tag = tagLibrary.getNodeHandler("foo");
            assertTrue(tags.remove(tag));
        }
        assertEquals(0, tags.size());
        assertEquals("foo: " + SIZE, tagLibrary.listUnreleasedTags());

        // and now with tags that can't be recycled
        tagLibrary = new TestTagLibrary();

        // get tag instances
        for (int i = 0; i < SIZE; i++) {
            tags.add(tagLibrary.getNodeHandler("bar"));
        }
        assertEquals(SIZE, tags.size());
        assertEquals("bar: " + SIZE, tagLibrary.listUnreleasedTags());

        // release tag instances
        iter = tags.iterator();
        while (iter.hasNext()) {
            NodeHandler tag = (NodeHandler) iter.next();
            tagLibrary.releaseNodeHandler("bar", tag);
        }
        assertEquals("", tagLibrary.listUnreleasedTags());

        // get tags again, checking them off against our list to make sure
        // they *haven't* been recycled
        for (int i = 0; i < SIZE; i++) {
            NodeHandler tag = tagLibrary.getNodeHandler("bar");
            assertFalse(tags.contains(tag));
        }
        assertEquals("bar: " + SIZE, tagLibrary.listUnreleasedTags());
    }

    public void testTagRecycling() {
        ParserConfiguration p = new ParserConfiguration();
        AbstractTagLibrary tagLibrary;
        NodeHandler tag1, tag2;

        p.addTagLibrary("recycle", new MockTagLibrary("tag", MockTag.class));
        p.addTagLibrary("discard", new MockTagLibrary("tag", MockRodTag.class));

        tagLibrary = (AbstractTagLibrary) p.findTagLibrary("recycle");
        tag1 = tagLibrary.getNodeHandler("tag");
        assertNotNull(tag1);
        tagLibrary.releaseNodeHandler("tag", tag1);
        tag2 = tagLibrary.getNodeHandler("tag");
        assertSame(tag1, tag2);
        tagLibrary.releaseNodeHandler("tag", tag2);
        assertEquals("", tagLibrary.listUnreleasedTags());

        tagLibrary = (AbstractTagLibrary) p.findTagLibrary("discard");
        tag1 = tagLibrary.getNodeHandler("tag");
        assertNotNull(tag1);
        tagLibrary.releaseNodeHandler("tag", tag1);
        tag2 = tagLibrary.getNodeHandler("tag");
        assertNotSame(tag1, tag2);
        tagLibrary.releaseNodeHandler("tag", tag2);
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
