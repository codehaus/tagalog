/*
 * $Id: AbstractTag.java,v 1.17 2005-05-17 21:15:47 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * AbstractTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.17 $
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

    /**
     * @deprecated use alternatively signitured requireAttribute method
     */
    protected final String requireAttribute(Attributes attributes,
                                            String elementName,
                                            String attributeName)
        throws TagException
    {
        return requireAttribute(attributes, attributeName);
    }

    protected String requireAttribute(Attributes attributes,
                                      String attributeName)
        throws TagException
    {
        return TagUtils.requireAttribute(this, attributes,
                attributeName);
    }

}
