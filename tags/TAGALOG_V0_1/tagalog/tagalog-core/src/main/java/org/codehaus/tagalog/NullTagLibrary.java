/*
 * $Id: NullTagLibrary.java,v 1.1 2004-02-26 17:45:16 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * <code>NullTagLibrary</code> creates instances of a tag that does nothing.
 * This is used by {@link AbstractParser} to handle nodes for which no tag
 * can be found, allowing the parse to continue and more complete errors to
 * be generated..
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
final class NullTagLibrary implements TagLibrary {
    static class NullTag extends AbstractTag { }

    /**
     * Singleton instance of the tag library.
     */
    public static final NullTagLibrary INSTANCE = new NullTagLibrary();

    private NullTagLibrary() {
    }

    public Tag getTag(String element) {
        return new NullTag();
    }

    public void releaseTag(String element, Tag tag) {
    }
}
