/*
 * $Id: AbstractTag.java,v 1.2 2004-02-11 12:42:27 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * AbstractTag
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public abstract class AbstractTag implements Tag {
    private Map context;

    private Tag parent;

    public void setContext(Map context) {
        this.context = context;
    }

    protected Map getContext() {
        return context;
    }

    public void setParent(Tag parent) {
        this.parent = parent;
    }

    public Tag getParent() {
        return parent;
    }

    public void begin(String elementName) {
    }

    public void text(char[] characters, int start, int length) {
    }

    public void child(Object child) {
    }

    public Object end(String elementName) throws TagalogParseException {
        return null;
    }

    public boolean recycle() {
        return true;
    }

    //
    // Convenience methods to make tag implementation easier.
    //

    protected Tag findAncestorWithClass(Class tagClass) {
        return TagUtils.findAncestorWithClass(getParent(), tagClass);
    }

    protected Tag requireAncestor(String tagName, Class tagClass)
        throws TagalogParseException
    {
        Tag tag = findAncestorWithClass(tagClass);
        if (tag == null) {
            throw new TagalogParseException(tagName + " ancestor not found");
        }
        return tag;
    }
}
