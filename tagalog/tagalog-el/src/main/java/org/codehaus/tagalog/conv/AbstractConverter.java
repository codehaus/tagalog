/*
 * $Id: AbstractConverter.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * AbstractConverter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public abstract class AbstractConverter implements Converter {
    public Object convert(String text) throws ConverterException {
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
     * @throws ConverterException If the text is invalid.
     */
    protected void checkValid(String text) throws ConverterException {
    }

    protected abstract Object parse(String text) throws ConverterException;

    protected void checkInvalidCharacters(String s, String valid)
        throws ConverterException
    {
        for (int i = 0; i < s.length(); i++) {
            if (valid.indexOf(s.charAt(i)) == -1)
                throw new ConverterException("'" + s.charAt(i) + "'"
                                             + " is not a valid character in "
                                             + "'" + s + "'");
        }
    }
}
