/*
 * $Id: Test.java,v 1.1 2005-04-05 17:15:51 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.codehaus.tagalog.script.Script;

/**
 * A single unit test for a <code>Script</code>.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class Test extends Assert {
    private Script script;

    private String expectedOutput;

    private Map context;

    public Test(Script script, String expectedOutput) {
        if (script == null)
            throw new NullPointerException("script is null");
        if (expectedOutput == null)
            throw new NullPointerException("expected output is null");
        this.script = script;
        this.expectedOutput = expectedOutput;
    }

    public void run() throws Exception {
        StringWriter outputWriter = new StringWriter();

        if (context == null)
            context = new HashMap();
        else
            assertNull(context.get(Script.TAGALOG_OUT));
        context.put(Script.TAGALOG_OUT, outputWriter);

        script.execute(context);

        assertEquals(script.getName(), expectedOutput, outputWriter.toString());
    }
}
