/*
 * $Id: MockConverter.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * A mock implementation of {@link Converter}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class MockConverter implements Converter {
    public Object convert(String text) throws ConverterException {
        return text;
    }
}
