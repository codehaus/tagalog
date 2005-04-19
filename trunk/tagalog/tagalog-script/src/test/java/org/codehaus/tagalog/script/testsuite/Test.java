/*
 * $Id: Test.java,v 1.2 2005-04-19 20:53:02 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.codehaus.tagalog.ParseError;
import org.codehaus.tagalog.script.Script;

/**
 * A single unit test for a <code>Script</code>.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public final class Test extends Assert {
    private final Script script;

    private final ParseError[] parseErrors;

    private final String expectedOutput;

    private Map context;

    public Test(Script script, String expectedOutput) {
        if (script == null)
            throw new NullPointerException("script is null");
        if (expectedOutput == null)
            throw new NullPointerException("expected output is null");
        this.script = script;
        this.parseErrors = null;
        this.expectedOutput = expectedOutput;
    }

    public Test(Script script, ParseError[] parseErrors, String expectedOutput)
    {
        if (script == null)
            throw new NullPointerException("script is null");
        if (parseErrors == null)
            throw new NullPointerException("parse errors is null");
        if (expectedOutput == null)
            throw new NullPointerException("expected output is null");
        this.script = script;
        this.parseErrors = (ParseError[]) parseErrors.clone();
        this.expectedOutput = expectedOutput;
    }

    public int caughtParseErrors() {
        if (parseErrors == null)
            return 0;
        return parseErrors.length;
    }

    public void run() throws Exception {
        String result;

        if (parseErrors != null) {
            StringBuffer buffer = new StringBuffer();

            for (int i = 0; i < parseErrors.length; i++) {
                if (i > 0)
                    buffer.append('|');
                buffer.append(parseErrors[i].getMessage());
            }

            result = buffer.toString();
        } else {
            StringWriter outputWriter = new StringWriter();

            if (context == null)
                context = new HashMap();
            else
                assertNull(context.get(Script.TAGALOG_OUT));
            context.put(Script.TAGALOG_OUT, outputWriter);

            script.execute(context);

            result = outputWriter.toString();
        }

        assertEquals(script.getName(), expectedOutput, result);
    }
}
