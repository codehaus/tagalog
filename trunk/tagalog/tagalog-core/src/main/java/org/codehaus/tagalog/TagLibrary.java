/*
 * $Id: TagLibrary.java,v 1.1 2004-02-10 18:56:05 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * TagLibrary
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public interface TagLibrary {
    Tag getTag(String element);
    void releaseTag(String element, Tag tag);
}
