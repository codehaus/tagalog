/*
 * $Id: TagUtilsTest.java,v 1.2 2004-12-09 15:06:47 mhw Exp $
 */
package org.codehaus.tagalog;

import junit.framework.TestCase;


/**
 * Set of tests for {@link org.codehaus.tagalog.TagUtils}
 *
 * @author Kristopher Brown
 * @version $Revision: 1.2 $
 */
public class TagUtilsTest extends TestCase {

    public void testFindAncestorWithNullTag() {
        try {
            TagUtils.findAncestor(null, TagA.class);
            fail("expected NPE when finding ancestor from a null tag");
        } catch (NullPointerException e) {
        }
    }

    public void testFindAncestorWithNullClass() {
        Tag tagA1 = new TagA();
        try {
            TagUtils.findAncestor(tagA1, null);
            fail("expected NPE when finding ancestor with a null class");
        } catch (NullPointerException e) {
        }
    }

    public void testFindAncestorWithNoParent() {
        Tag child = new TagA();
        Tag ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNull("expected ancestor to be null", ancestor);
    }

    public void testFindAncestorWithSameClassForChildAndParentMatchesParent() {
        Tag child = new TagA();
        Tag parent = new TagA();
        child.setParent(parent);
        Tag ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", parent, ancestor);
    }

    public void testFindAncestorWithDifferentClassForChildAndParentNoMatch() {
        Tag child = new TagA();
        Tag parent = new TagB();
        child.setParent(parent);
        Tag ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNull("expected ancestor to be null", ancestor);
    }

    public void testFindAncestorWithDifferentClassForChildAndParentMatchesParent() {
        Tag child = new TagA();
        Tag parent = new TagB();
        child.setParent(parent);
        Tag ancestor = TagUtils.findAncestor(child, TagB.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", parent, ancestor);
    }

    public void testFindAncestorWithSameClassForParentAndGrandParentMatchesParent() {
        Tag child = new TagA();
        Tag parent = new TagA();
        Tag grandparent = new TagA();
        child.setParent(parent);
        parent.setParent(grandparent);
        Tag ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", parent, ancestor);
    }

    public void testFindAncestorWithSameClassForGrandParentMatchesGrandParent() {
        Tag child = new TagA();
        Tag parent = new TagB();
        Tag grandparent = new TagA();
        child.setParent(parent);
        parent.setParent(grandparent);
        Tag ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", grandparent, ancestor);
    }

    private static class TagA extends AbstractTag {
    }

    private static class TagB extends AbstractTag {
    }
}
