/*
 * $Id: MockTag.java,v 1.2 2004-02-11 01:17:20 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Mock tag implementation for testing.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class MockTag extends AbstractTag {
    private boolean recycled = false;

    public boolean isRecycled() {
        return recycled;
    }

    public boolean recycle() {
        recycled = true;
        return true;
    }
}
