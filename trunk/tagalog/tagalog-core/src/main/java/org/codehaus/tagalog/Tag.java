/*
 * $Id: Tag.java,v 1.2 2004-02-11 01:14:16 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * Tag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface Tag {
    void begin();
    Object end();
    boolean recycle();
}
