/*
 * $Id: IntConverter.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.math.BigInteger;

/**
 * IntConverter
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class IntConverter extends IntegerConverter implements Converter {
    protected int bitLength() {
        return 32;
    }

    protected Object coerce(BigInteger value) throws ConverterException {
        return new Integer(value.intValue());
    }
}
