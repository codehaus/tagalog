/*
 * $Id: BigIntegerConverter.java,v 1.1 2005-03-01 10:31:25 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * BigIntegerConverter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public abstract class BigIntegerConverter
    extends AbstractConverter
    implements Converter
{
    protected static final String INTEGER_CHARS = "0123456789+-";

    protected void checkValid(String text) throws ConverterException {
        checkInvalidCharacters(text, validCharacters());
    }

    protected String validCharacters() {
        return INTEGER_CHARS;
    }

    protected Object parse(String text) throws ConverterException {
        BigInteger value = new BigInteger(text);
        if (value.bitLength() >= bitLength())
            throw new ConverterException("'" + value + "' is too large");
        return coerce(value);
    }

    protected abstract int bitLength();

    protected abstract Object coerce(BigInteger value)
        throws ConverterException;
}
