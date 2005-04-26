/*
 * $Id: TagLibrary.java,v 1.2 2005-04-26 14:26:38 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * TagLibrary
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface TagLibrary {
    NodeHandler getNodeHandler(String name);
    void releaseNodeHandler(String name, NodeHandler handler);
}
