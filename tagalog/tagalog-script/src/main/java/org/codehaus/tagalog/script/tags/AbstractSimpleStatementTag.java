/*
 * $Id: AbstractSimpleStatementTag.java,v 1.2 2005-04-26 15:32:46 mhw Exp $
 */

package org.codehaus.tagalog.script.tags;

import org.codehaus.tagalog.TagBinding;
import org.codehaus.tagalog.TagException;
import org.codehaus.tagalog.TagalogParseException;

/**
 * Shared behaviour for simple atomic statement tags (i.e. those that
 * do not contain other content and XML elements).
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public abstract class AbstractSimpleStatementTag
    extends AbstractStatementTag
{
    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        String elementName = getTagBinding().getName();

        throw new TagException("<" + elementName + ">"
                               + " cannot contain XML elements in its body");
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
        String elementName = getTagBinding().getName();

        throw new TagException("<" + elementName + ">"
                               + " cannot contain text content in its body");
    }
}
