/*
 * $Id: BooleanConverter.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Converter for the Boolean datatypes. Recognises the values
 * <code>"true"</code>, <code>"on"</code> and <code>"yes"</code>
 * as indicating <em>true</em> and
 * <code>"false"</code>, <code>"off"</code> and <code>"no"</code>
 * as indicating <em>false</em>.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class BooleanConverter extends AbstractConverter implements Converter {

    protected Object parse(String text) throws ConversionException {
        if (text.equals("true") || text.equals("on") || text.equals("yes"))
            return Boolean.TRUE;
        if (text.equals("false") || text.equals("off") || text.equals("no"))
            return Boolean.FALSE;
        throw new ConversionException("invalid Boolean '" + text + "'");
    }
}
