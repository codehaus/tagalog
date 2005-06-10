/*
 * $Id: ShortConverter.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * Converter for 16-bit integer values.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ShortConverter extends BigIntegerConverter implements Converter {
    protected int bitLength() {
        return 16;
    }

    protected Object coerce(BigInteger value) throws ConversionException {
        return new Short(value.shortValue());
    }
}
