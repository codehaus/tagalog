/*
 * $Id: BigIntegerConverter.java,v 1.3 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * BigIntegerConverter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public abstract class BigIntegerConverter
    extends AbstractConverter
    implements Converter
{
    protected static final String INTEGER_CHARS = "0123456789";

    protected void checkValid(String text) throws ConversionException {
        char firstChar = text.charAt(0);
        if (firstChar == '+' || firstChar == '-')
            text = text.substring(1);
        checkInvalidCharacters(text, validCharacters());
    }

    protected String validCharacters() {
        return INTEGER_CHARS;
    }

    protected Object parse(String text) throws ConversionException {
        if (text.charAt(0) == '+')
            text = text.substring(1);
        BigInteger value = new BigInteger(text);
        if (value.bitLength() >= bitLength())
            throw new ConversionException("'" + value + "' is too large");
        return coerce(value);
    }

    protected abstract int bitLength();

    protected abstract Object coerce(BigInteger value)
        throws ConversionException;
}
