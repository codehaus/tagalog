/*
 * $Id: AbstractTag.java,v 1.15 2005-05-17 14:17:47 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * AbstractTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.15 $
 */
public abstract class AbstractTag extends AbstractNodeHandler implements Tag {

    public void begin(String elementName, Attributes attributes)
        throws TagException
    {
    }

    public void text(char[] characters, int start, int length)
        throws TagException
    {
    }

    public void child(TagBinding childType, Object child) throws TagException {
    }

    public Object end(String elementName) throws TagException {
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
