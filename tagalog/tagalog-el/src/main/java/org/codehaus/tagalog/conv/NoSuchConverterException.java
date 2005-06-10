/*
 * $Id: NoSuchConverterException.java,v 1.2 2005-06-10 12:38:38 krisb Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Thrown if a suitable {@link Converter} could not be found.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class NoSuchConverterException extends ConverterException {
    private final Class targetClass;

    /**
     * @param targetClass The class for which a converter could not be found.
     */
    public NoSuchConverterException(Class targetClass) {
        super("converter could not be found for target " + targetClass);
        this.targetClass = targetClass;
    }

    public Class getTargetClass() {
        return targetClass;
    }
}
