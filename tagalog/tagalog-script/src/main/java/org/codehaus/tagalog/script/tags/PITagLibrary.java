/*
 * $Id: PITagLibrary.java,v 1.1 2005-04-26 15:30:37 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.AbstractTagLibrary;
import org.codehaus.tagalog.TagBinding;

/**
 * Processing instruction tag library for tagalog scripts.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class PITagLibrary extends AbstractTagLibrary {
    public static final TagBinding
                SCRIPT_EL = new TagBinding("script-el",
                                           ExpressionLanguagePIHandler.class);

    public PITagLibrary() {
        registerTagBinding(SCRIPT_EL);
    }
}
