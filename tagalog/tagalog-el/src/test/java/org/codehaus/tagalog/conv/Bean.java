/*
 * $Id: Bean.java,v 1.1 2004-12-20 19:02:49 mhw Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Sample bean for property setting tests.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class Bean {

    private byte byteValue;
    private char charValue;
    private short shortValue;
    private int intValue;
    private long longValue;

    public byte getByteValue() {
        return byteValue;
    }
    public void setByteValue(byte byteValue) {
        this.byteValue = byteValue;
    }
    public char getCharValue() {
        return charValue;
    }
    public void setCharValue(char charValue) {
        this.charValue = charValue;
    }
    public int getIntValue() {
        return intValue;
    }
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
    public long getLongValue() {
        return longValue;
    }
    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }
    public short getShortValue() {
        return shortValue;
    }
    public void setShortValue(short shortValue) {
        this.shortValue = shortValue;
    }
}
