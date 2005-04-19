/*
 * $Id: AbstractStatement.java,v 1.3 2005-04-19 14:09:36 mhw Exp $
 */

package org.codehaus.tagalog.script;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Abstract base class for implementations of {@link Statement}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.3 $
 */
public abstract class AbstractStatement implements Statement {

    protected void output(Map context, String string) throws IOException {
        Object o = context.get(Script.TAGALOG_OUT);
        if (o instanceof Writer)
            ((Writer) o).write(string);
    }
}
