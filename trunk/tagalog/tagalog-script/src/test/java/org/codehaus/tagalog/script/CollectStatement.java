/*
 * $Id: CollectStatement.java,v 1.2 2005-04-26 17:04:43 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class CollectStatement implements Statement {
    public String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void execute(Map context) throws ScriptException {
        List collection = (List) context.get("collection");
        collection.add(content);
    }
}
