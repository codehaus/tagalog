/*
 * $Id: AbstractSimpleStatementTag.java,v 1.1 2005-04-19 16:38:13 mhw Exp $
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
 * @version $Revision: 1.1 $
 */
public abstract class AbstractSimpleStatementTag
    extends AbstractStatementTag
{
    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
        String elementName = getTagBinding().getElementName();

        throw new TagException("<" + elementName + ">"
                               + " cannot contain XML elements in its body");
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
        String elementName = getTagBinding().getElementName();

        throw new TagException("<" + elementName + ">"
                               + " cannot contain text content in its body");
    }
}
