/*
 * $Id: Bean.java,v 1.2 2005-03-01 10:34:34 mhw Exp $
 */

package org.codehaus.tagalog.conv;

import java.net.URL;

/**
 * Sample bean for property setting tests.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class Bean {
    private boolean booleanValue;

    private byte byteValue;
    private short shortValue;
    private int integerValue;
    private long longValue;

    private char charValue;

    private URL urlValue;

    public boolean getBooleanValue() {
        return booleanValue;
    }
    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
    public void setBooleanObjectValue(Boolean booleanValue) {
        this.booleanValue = booleanValue.booleanValue();
    }

    public byte getByteValue() {
        return byteValue;
    }
    public void setByteValue(byte byteValue) {
        this.byteValue = byteValue;
    }
    public void setByteObjectValue(Byte byteValue) {
        this.byteValue = byteValue.byteValue();
    }

    public short getShortValue() {
        return shortValue;
    }
    public void setShortValue(short shortValue) {
        this.shortValue = shortValue;
    }
    public void setShortObjectValue(Short shortValue) {
        this.shortValue = shortValue.shortValue();
    }

    public int getIntegerValue() {
        return integerValue;
    }
    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }
    public void setIntegerObjectValue(Integer integerValue) {
        this.integerValue = integerValue.intValue();
    }

    public long getLongValue() {
        return longValue;
    }
    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }
    public void setLongObjectValue(Long longValue) {
        this.longValue = longValue.longValue();
    }

    public char getCharValue() {
        return charValue;
    }
    public void setCharValue(char charValue) {
        this.charValue = charValue;
    }

    public URL getURLValue() {
        return urlValue;
    }
    public void setURLValue(URL urlValue) {
        this.urlValue = urlValue;
    }
}
