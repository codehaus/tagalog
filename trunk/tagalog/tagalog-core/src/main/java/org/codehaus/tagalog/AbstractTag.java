/*
 * $Id: AbstractTag.java,v 1.1 2004-02-11 01:15:26 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * AbstractTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public abstract class AbstractTag implements Tag {
    public void begin() {
    }

    public Object end() {
        return null;
    }

    public boolean recycle() {
        return true;
    }
}
