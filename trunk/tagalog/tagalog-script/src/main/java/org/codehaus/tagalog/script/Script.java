/*
 * $Id: Script.java,v 1.4 2005-04-19 16:28:01 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * Representation of a script, which attaches a name to a
 * {@link Statement}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.4 $
 */
public final class Script {
    public static final String TAGALOG_OUT = "tagalog.out";

    private String name;

    private Statement body;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBody(Statement body) {
        this.body = body;
    }

    public void execute(Map context) throws Exception {
        body.execute(context);
    }
}
