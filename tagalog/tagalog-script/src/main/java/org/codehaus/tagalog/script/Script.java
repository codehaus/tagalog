/*
 * $Id: Script.java,v 1.3 2005-04-05 17:12:21 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * Representation of a script, which attaches a name to a
 * {@link Sequence}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.3 $
 */
public final class Script {
    public static final String TAGALOG_OUT = "tagalog.out";

    private String name;

    private Sequence body;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBody(Sequence body) {
        this.body = body;
    }

    public void execute(Map context) throws Exception {
        body.execute(context);
    }
}
