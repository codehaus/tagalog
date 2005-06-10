/*
 * $Id: MockConverter.java,v 1.2 2005-06-10 12:40:31 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * A mock implementation of {@link Converter}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class MockConverter implements Converter {
    public Object convert(String text) throws ConversionException {
        return text;
    }
}
