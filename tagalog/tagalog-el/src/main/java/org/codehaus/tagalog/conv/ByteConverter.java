/*
 * $Id: ByteConverter.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * Converter for 8-bit integer values.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ByteConverter extends BigIntegerConverter implements Converter {
    protected int bitLength() {
        return 8;
    }

    protected Object coerce(BigInteger value) throws ConversionException {
        return new Byte(value.byteValue());
    }
}
