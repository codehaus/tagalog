/*
 * $Id: BigIntegerConverter.java,v 1.2 2005-03-01 11:59:26 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * BigIntegerConverter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public abstract class BigIntegerConverter
    extends AbstractConverter
    implements Converter
{
    protected static final String INTEGER_CHARS = "0123456789";

    protected void checkValid(String text) throws ConverterException {
        char firstChar = text.charAt(0);
        if (firstChar == '+' || firstChar == '-')
            text = text.substring(1);
        checkInvalidCharacters(text, validCharacters());
    }

    protected String validCharacters() {
        return INTEGER_CHARS;
    }

    protected Object parse(String text) throws ConverterException {
        if (text.charAt(0) == '+')
            text = text.substring(1);
        BigInteger value = new BigInteger(text);
        if (value.bitLength() >= bitLength())
            throw new ConverterException("'" + value + "' is too large");
        return coerce(value);
    }

    protected abstract int bitLength();

    protected abstract Object coerce(BigInteger value)
        throws ConverterException;
}
