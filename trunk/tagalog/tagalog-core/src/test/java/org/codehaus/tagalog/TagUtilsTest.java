/*
 * $Id: TagUtilsTest.java,v 1.3 2005-04-26 14:33:00 mhw Exp $
 */
package org.codehaus.tagalog;

import junit.framework.TestCase;

/**
 * Set of tests for {@link org.codehaus.tagalog.TagUtils}
 *
 * @author Kristopher Brown
 * @version $Revision: 1.3 $
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
        NodeHandler tagA1 = new TagA();
        try {
            TagUtils.findAncestor(tagA1, null);
            fail("expected NPE when finding ancestor with a null class");
        } catch (NullPointerException e) {
        }
    }

    public void testFindAncestorWithNoParent() {
        NodeHandler child = new TagA();
        NodeHandler ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNull("expected ancestor to be null", ancestor);
    }

    public void testFindAncestorWithSameClassForChildAndParentMatchesParent() {
        NodeHandler child = new TagA();
        NodeHandler parent = new TagA();
        child.setParent(parent);
        NodeHandler ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", parent, ancestor);
    }

    public void testFindAncestorWithDifferentClassForChildAndParentNoMatch() {
        NodeHandler child = new TagA();
        NodeHandler parent = new TagB();
        child.setParent(parent);
        NodeHandler ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNull("expected ancestor to be null", ancestor);
    }

    public void testFindAncestorWithDifferentClassForChildAndParentMatchesParent() {
        NodeHandler child = new TagA();
        NodeHandler parent = new TagB();
        child.setParent(parent);
        NodeHandler ancestor = TagUtils.findAncestor(child, TagB.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", parent, ancestor);
    }

    public void testFindAncestorWithSameClassForParentAndGrandParentMatchesParent() {
        NodeHandler child = new TagA();
        NodeHandler parent = new TagA();
        NodeHandler grandparent = new TagA();
        child.setParent(parent);
        parent.setParent(grandparent);
        NodeHandler ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", parent, ancestor);
    }

    public void testFindAncestorWithSameClassForGrandParentMatchesGrandParent() {
        NodeHandler child = new TagA();
        NodeHandler parent = new TagB();
        NodeHandler grandparent = new TagA();
        child.setParent(parent);
        parent.setParent(grandparent);
        NodeHandler ancestor = TagUtils.findAncestor(child, TagA.class);
        assertNotNull("expected ancestor to be non-null", ancestor);
        assertEquals("expected ancestor to be parent", grandparent, ancestor);
    }

    private static class TagA extends AbstractTag {
    }

    private static class TagB extends AbstractTag {
    }
}
