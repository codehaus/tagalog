/*
 * $Id: MockRodTag.java,v 1.2 2004-02-11 01:17:20 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Mock tag implementation for testing.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class MockRodTag extends AbstractTag {
    public boolean recycle() {
        return false;
    }
}
