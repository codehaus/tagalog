/*
 * $Id: IntegerConverter.java,v 1.3 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * Converter for 32-bit integer values.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public class IntegerConverter extends BigIntegerConverter implements Converter {
    protected int bitLength() {
        return 32;
    }

    protected Object coerce(BigInteger value) throws ConversionException {
        return new Integer(value.intValue());
    }
}
