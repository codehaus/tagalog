/*
 * $Id: AbstractSimpleStatementTag.java,v 1.3 2005-05-17 14:23:10 krisb Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;

/**
 * Shared behaviour for simple atomic statement tags (i.e. those that
 * do not contain other content and XML elements).
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public abstract class AbstractSimpleStatementTag
    extends AbstractStatementTag
{
    public void child(TagBinding childType, Object child) throws TagException {
        String elementName = getTagBinding().getName();

        throw new TagException("<" + elementName + ">"
                               + " cannot contain XML elements in its body");
    }

    public void text(char[] characters, int start, int length)
        throws TagException
    {
        String elementName = getTagBinding().getName();

        throw new TagException("<" + elementName + ">"
                               + " cannot contain text content in its body");
    }
}
