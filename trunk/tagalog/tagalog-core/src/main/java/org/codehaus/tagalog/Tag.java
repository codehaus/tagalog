/*
 * $Id: Tag.java,v 1.7 2004-05-06 22:31:42 mhw Exp $
 */

package org.codehaus.tagalog;

import java.util.Map;

/**
 * The <code>Tag</code> interface defines the basic lifecycle of a tag, an
 * object that is responsible for processing the content of an XML element.
 * The <code>Tag</code> lifecycle is:
 *
 * <ul>
 * <li>
 * An instance of the tag is constructed using either its default
 * constructor, or a non-default constructor invoked by a {@link TagFactory}.
 * <li>
 * The {@link #setParser}, {@link #setContext} and {@link #setParent} methods
 * are called to connect the tag instance to the parser, the shared parse
 * context and the tag's parent.
 * If the tag is processing the root element of the document then
 * <code>setParent</code> will not be called.
 * <li>
 * The {@link #begin} method is called. Tags should instantiate any state
 * they need to maintain and process attribute values within this method.
 * <li>
 * The {@link #text} and {@link #child} methods are called as the text
 * content and child elements are processed. <code>child</code> is called
 * after the child tag has completed its lifecycle; the return value from
 * the child's <code>end</code> method is supplied as a parameter to the
 * <code>child</code> method.
 * <li>
 * The {@link #end} method is called. The return value of this method will
 * be passed to the <code>child</code> method of the parent tag.
 * <li>
 * The {@link #setContext}, {@link #setParent} and {@link #setParser}
 * methods are called with the parameter <code>null</code> to disconnect
 * the tag instance from the shared parse context, the tag's parent and
 * the parser.
 * If the tag is processing the root element of the document then
 * <code>setParent</code> will not be called.
 * <li>
 * The {@link #recycle} method is called to enquire whether the tag instance
 * can be reused. Implementations of this method can either discard any
 * internal state (by setting references to <code>null</code> and return
 * <code>true</code>, or they can return <code>false</code>, in which case
 * the tag instance is discarded by the parser.
 * </ul>
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.7 $
 */
public interface Tag {
    void setParser(TagalogParser parser);

    void setContext(Map context);

    void setParent(Tag parent);

    Tag getParent();

    void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException;

    void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException;

    void child(Object child) throws TagException, TagalogParseException;

    Object end(String elementName) throws TagException, TagalogParseException;

    boolean recycle();
}
