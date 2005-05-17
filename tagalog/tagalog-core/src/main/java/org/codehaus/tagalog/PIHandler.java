/*
 * $Id: PIHandler.java,v 1.2 2005-05-17 14:17:47 krisb Exp $
 */

package org.codehaus.tagalog;

/**
 * The <code>ProcessingInstruction</code> interface defines the basic
 * lifecycle of a processing instruction handler, a {@link NodeHandler}
 * that is responsible for processing an XML processing instruction.
 * The <code>ProcessingInstruction</code> lifecycle is:
 *
 * <ul>
 * <li>
 * An instance of the processing instruction handler is constructed as
 * per the {@linkplain NodeHandler#init Initialisation} phase.
 * </li>
 * <li>
 * The instance is connected to the parser state as per the
 * {@linkplain NodeHandler#link Linking} phase.
 * </li>
 * <li>
 * The {@link #processingInstruction} method is called. The return value
 * of this method will be passed to the <code>child</code> method of the
 * parent tag.
 * </li>
 * <li>
 * The instance is disconnected from the parse state as per the
 * {@linkplain NodeHandler#unlink Unlinking} phase.
 * </li>
 * </ul>
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public interface PIHandler extends NodeHandler {
    void processingInstruction(String target, String data) throws TagException;
}
