/*
 * $Id: TagalogParseException.java,v 1.4 2005-05-17 10:57:51 mhw Exp $
 */

package org.codehaus.tagalog;

/**
 * <code>TagalogParseException</code> is used to wrap any exceptions
 * thrown by the underlying XML parser. It can also be thrown by
 * implementations of the {@link Tag} interface if they encounter a
 * condition that makes it impossible to continue processing.
 * However, this style of processing is discouraged: in the majority of
 * cases it is preferrable for <code>Tag</code>s to throw
 * {@link TagException} as this allows parsing to continue and a better
 * error report to be prepared for the user.
 *
 * @see TagException
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.4 $
 */
public class TagalogParseException extends Exception {
    public TagalogParseException(Throwable cause) {
        super(cause);
    }
}
