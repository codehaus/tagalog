/*
 * $Id: ShortConverter.java,v 1.1 2005-03-01 10:32:01 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * Converter for 16-bit integer values.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ShortConverter extends BigIntegerConverter implements Converter {
    protected int bitLength() {
        return 16;
    }

    protected Object coerce(BigInteger value) throws ConverterException {
        return new Short(value.shortValue());
    }
}
