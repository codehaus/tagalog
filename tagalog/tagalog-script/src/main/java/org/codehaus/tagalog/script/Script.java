/*
 * $Id: Script.java,v 1.2 2004-11-08 07:23:35 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.Map;

/**
 * Representation of a script, which attaches a name to a
 * {@link Sequence}.
 *
 * @author Mark H. Wilkinson
 * @version $Revision: 1.2 $
 */
public final class Script {
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
