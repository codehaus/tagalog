/*
 * $Id: Tag.java,v 1.9 2005-04-26 14:22:39 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * The <code>Tag</code> interface defines the basic lifecycle of a tag, a
 * {@link NodeHandler} that is responsible for processing the content of
 * an XML element.
 * The <code>Tag</code> lifecycle is:
 *
 * <ul>
 * <li>
 * An instance of the tag is constructed as per the
 * {@linkplain NodeHandler#init Initialisation} phase.
 * </li>
 * <li>
 * The instance is connected to the parser state as per the
 * {@linkplain NodeHandler#link Linking} phase.
 * </li>
 * <li>
 * The {@link #begin} method is called. Tags should instantiate any state
 * they need to maintain and process attribute values within this method.
 * </li>
 * <li>
 * The {@link #text} and {@link #child} methods are called as the text
 * content and child elements are processed. <code>child</code> is called
 * after the child tag has completed its lifecycle; the return value from
 * the child's <code>end</code> method is supplied as a parameter to the
 * <code>child</code> method.
 * </li>
 * <li>
 * The {@link #end} method is called. The return value of this method will
 * be passed to the <code>child</code> method of the parent tag.
 * </li>
 * <li>
 * The instance is disconnected from the parse state as per the
 * {@linkplain NodeHandler#unlink Unlinking} phase.
 * </li>
 * </ul>
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.9 $
 */
public interface Tag extends NodeHandler {
    void begin(String elementName, Attributes attributes)
        throws TagException, TagalogParseException;

    void text(char[] characters, int start, int length)
        throws TagException, TagalogParseException;

    void child(TagBinding childType, Object child)
        throws TagException, TagalogParseException;

    Object end(String elementName) throws TagException, TagalogParseException;
}
