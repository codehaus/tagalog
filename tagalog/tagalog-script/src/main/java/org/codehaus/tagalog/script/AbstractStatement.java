/*
 * $Id: AbstractStatement.java,v 1.1 2004-11-04 17:54:48 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * AbstractStatement
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public abstract class AbstractStatement implements Statement {

    protected void output(Map context, String string) throws IOException {
        Object o = context.get("tagalog.out");
        if (o instanceof Writer)
            ((Writer) o).write(string);
    }
}
