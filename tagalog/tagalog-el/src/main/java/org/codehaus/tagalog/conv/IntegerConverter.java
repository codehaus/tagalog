/*
 * $Id: IntegerConverter.java,v 1.2 2005-03-01 10:31:25 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * Converter for 32-bit integer values.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class IntegerConverter extends BigIntegerConverter implements Converter {
    protected int bitLength() {
        return 32;
    }

    protected Object coerce(BigInteger value) throws ConverterException {
        return new Integer(value.intValue());
    }
}
