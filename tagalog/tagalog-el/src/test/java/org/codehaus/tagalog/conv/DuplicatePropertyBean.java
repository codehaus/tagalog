/*
 * $Id: DuplicatePropertyBean.java,v 1.2 2005-03-30 11:41:00 mhw Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * A bean that has property setters of overloaded types.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class DuplicatePropertyBean {
    private int intValue;

    public int getIntValue() {
        return intValue;
    }
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
    public void setIntValue(String stringValue) {
        this.intValue = stringValue.length();
    }
    public void setIntValue(StringBuffer stringBufferValue) {
        this.intValue = stringBufferValue.length();
    }
}
