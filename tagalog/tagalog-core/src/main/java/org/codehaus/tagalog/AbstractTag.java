/*
 * $Id: AbstractTag.java,v 1.14 2005-04-26 14:25:08 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * AbstractTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.14 $
 */
public abstract class AbstractTag extends AbstractNodeHandler implements Tag {

    public void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException
    {
    }

    public void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException
    {
    }

    public void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException
    {
    }

    public Object end(String elementName)
        throws TagException, TagalogParseException
    {
        return null;
    }

    //
    // Convenience methods to make tag implementation easier.
    //

    protected final String requireAttribute(Attributes attributes,
                                            String elementName,
                                            String attributeName)
        throws TagException
    {
        return TagUtils.requireAttribute(attributes, elementName,
                                         attributeName);
    }
}
