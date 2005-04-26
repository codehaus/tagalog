/*
 * $Id: Bean.java,v 1.1 2005-04-26 16:41:25 mhw Exp $
 */

package org.codehaus.tagalog.script.core;

/**
 * A simple JavaBean.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public class Bean {
    private String beanProperty;

    public String getBeanProperty() {
        return beanProperty;
    }

    public void setBeanProperty(String beanProperty) {
        this.beanProperty = beanProperty;
    }
}
