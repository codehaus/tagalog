/*
 * $Id: TestSuite.java,v 1.1 2005-04-05 17:15:51 mhw Exp $
 */

package org.codehaus.tagalog.script.testsuite;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of script unit tests.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.1 $
 */
public final class TestSuite {
    private List tests = new LinkedList();

    public void addTest(Test test) {
        tests.add(test);
    }

    public void run() throws Exception {
        Iterator iter = tests.iterator();

        while (iter.hasNext()) {
            Test test = (Test) iter.next();
            test.run();
        }
    }
}
