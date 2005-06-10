/*
 * $Id: AbstractConverter.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * AbstractConverter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public abstract class AbstractConverter implements Converter {
    public Object convert(String text) throws ConversionException {
        if (text == null)
            return null;
        text = text.trim();
        if (text.length() == 0)
            return null;
        checkValid(text);
        return parse(text);
    }

    /**
     * Check whether the text supplied is valid for this converter.
     * <em>This</em> implementation performs no validation.
     *
     * @param text Text to validate.
     * @throws ConversionException If the text is invalid.
     */
    protected void checkValid(String text) throws ConversionException {
    }

    protected abstract Object parse(String text) throws ConversionException;

    protected void checkInvalidCharacters(String s, String valid)
        throws ConversionException
    {
        for (int i = 0; i < s.length(); i++) {
            if (valid.indexOf(s.charAt(i)) == -1)
                throw new ConversionException("'" + s.charAt(i) + "'"
                                             + " is not a valid character in "
                                             + "'" + s + "'");
        }
    }
}
