/*
 * $Id: MockTagLibrary.java,v 1.2 2005-04-07 15:49:12 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Mock implementation of {@link TagLibrary}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class MockTagLibrary extends AbstractTagLibrary {
    public MockTagLibrary() {
    }

    public MockTagLibrary(String tagName, Class tagClass) {
        registerTagBinding(new TagBinding(tagName, tagClass));
    }
}
