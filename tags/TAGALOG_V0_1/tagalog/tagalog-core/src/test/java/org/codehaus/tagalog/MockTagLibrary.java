/*
 * $Id: MockTagLibrary.java,v 1.1 2004-02-10 18:56:05 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Mock implementation of {@link TagLibrary}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class MockTagLibrary extends AbstractTagLibrary {
    public MockTagLibrary() {
    }

    public MockTagLibrary(String tagName, Class tagClass) {
        registerTag(tagName, tagClass);
    }
}
