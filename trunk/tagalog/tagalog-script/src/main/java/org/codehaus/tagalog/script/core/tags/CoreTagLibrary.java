/*
 * $Id: CoreTagLibrary.java,v 1.1 2004-11-04 18:05:28 mhw Exp $
 */

package org.codehaus.tagalog.script.core.tags;

import org.codehaus.tagalog.AbstractTagLibrary;

/**
 * @author Mark H. Wilkinson
 * @version $Revision: 1.1 $
 */
public class CoreTagLibrary extends AbstractTagLibrary {
    public static final String NS_URI
        = "tagalog:core";

    public CoreTagLibrary() {
        registerTag("out", OutTag.class);
    }
}
