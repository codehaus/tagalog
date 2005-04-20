/*
 * $Id: Script.java,v 1.5 2005-04-20 16:01:09 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * Representation of a script, which attaches a name to a
 * {@link Statement}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.5 $
 */
public final class Script {
    /**
     * Context key holding the script output {@link java.io.Writer}.
     */
    public static final String TAGALOG_OUT = "tagalog.out";

    /**
     * Context key holding the expression language
     * {@link org.codehaus.tagalog.el.ParseController}.
     */
    public static final String TAGALOG_EL_PARSER = "tagalog.el.parser";

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
