/*
 * $Id: ChildBean.java,v 1.1 2005-03-01 10:34:34 mhw Exp $
 */

package org.codehaus.tagalog.conv;

/**
 * Sample bean to test property setting works for inherited fields.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class ChildBean extends Bean {
    private int childIntegerValue;

    public int getChildIntegerValue() {
        return childIntegerValue;
    }
    public void setChildIntegerValue(int childIntegerValue) {
        this.childIntegerValue = childIntegerValue;
    }

    public void setIntegerValue(int integerValue) {
        super.setIntegerValue(integerValue * 2);
    }
}
