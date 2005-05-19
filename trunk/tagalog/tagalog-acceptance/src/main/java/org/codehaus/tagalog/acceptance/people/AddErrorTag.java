/*
 * $Id: AddErrorTag.java,v 1.1 2005-05-19 11:40:15 krisb Exp $
 */

package org.codehaus.tagalog.acceptance.people;

import org.codehaus.tagalog.AbstractTag;
import org.codehaus.tagalog.Attributes;
import org.codehaus.tagalog.TagException;

/**
 * A Tag that throws a TagException with an underlying cause.
 *
 * @author <a href="mailto:krisb@codehaus.org">Kristopher Brown</a>
 * @version $Revision: 1.1 $
 */
public final class AddErrorTag extends AbstractTag {
    public void begin(String elementName, Attributes attributes)
            throws TagException
    {
        getParser().addError("manual error reporting");
        throw new TagException();
    }
}
