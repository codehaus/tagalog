/*
 * $Id: ParseControllerTest.java,v 1.2 2005-04-20 15:57:07 mhw Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import junit.framework.TestCase;

/**
 * Tests for {@link org.codehaus.tagalog.el.ParseController}.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.2 $
 */
public class ParseControllerTest extends TestCase {

    private ParseController copy;

    protected void setUp() {
        copy = (ParseController) ParseController.DEFAULT.clone();
    }

    public void assertEquals(String[] expected, String[] actual) {
        HashSet expectedSet = new HashSet(Arrays.asList(expected));
        HashSet actualSet = new HashSet(Arrays.asList(actual));
        assertEquals(expectedSet, actualSet);
    }

    public void testClone() {
        // copy already contains a clone

        assertEquals(ParseController.DEFAULT.expressionLanguageNames(),
                     copy.expressionLanguageNames());
    }

    public void testMakeImmutable() {
        copy.addExpressionLanguage("one",
                                   new ContextValueExpressionParser());
        copy.makeImmutable();
        try {
            copy.addExpressionLanguage("two",
                                       new ContextValueExpressionParser());
            fail("not immutable");
        } catch (IllegalStateException e) {
            // expected
        }

        ParseController copy2 = (ParseController) copy.clone();
        copy2.addExpressionLanguage("two",
                                    new ContextValueExpressionParser());
        assertEquals(new String[] {
                         ParseController.STANDARD,
                         ParseController.COMPOUND,
                         "one",
                         "two",
                     },
                     copy2.expressionLanguageNames());
    }

    public void testAddExpressionLanguage() {
        copy.addExpressionLanguage("one",
                                   new ContextValueExpressionParser());
        assertEquals(new String[] {
                         ParseController.STANDARD,
                         ParseController.COMPOUND,
                         "one",
                     },
                     copy.expressionLanguageNames());

        try {
            copy.addExpressionLanguage("one",
                                       new ContextValueExpressionParser());
            fail("replaced expression language");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testReplaceExpressionLanguage() throws Exception {
        copy.addExpressionLanguage("one",
                                   new ContextValueExpressionParser());
        assertEquals(new String[] {
                         ParseController.STANDARD,
                         ParseController.COMPOUND,
                         "one",
                     },
                     copy.expressionLanguageNames());

        copy.replaceExpressionLanguage("one",
                                       new ContextValueExpressionParser());
        assertEquals(new String[] {
                         ParseController.STANDARD,
                         ParseController.COMPOUND,
                         "one",
                     },
                     copy.expressionLanguageNames());

        assertEquals(":", copy.evaluate(":${foo}", new HashMap()));

        copy.replaceExpressionLanguage(ParseController.STANDARD,
                                       new LiteralExpressionParser());

        assertEquals(":foo", copy.evaluate(":${foo}", new HashMap()));
    }
}
