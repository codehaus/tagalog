/*
 * $Id: ParseController.java,v 1.7 2005-06-10 07:39:13 krisb Exp $
 */

package org.codehaus.tagalog.el;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Main entry point for expression parsing.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.7 $
 */
public final class ParseController implements Cloneable {
    private TreeMap languagesByName = new TreeMap();

    private boolean immutable = false;

    public void makeImmutable() {
        immutable = true;
    }

    public Object clone() {
        ParseController result;
        try {
            result = (ParseController) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("clone failed: " + e.getMessage());
        }
        result.languagesByName = (TreeMap) this.languagesByName.clone();
        result.immutable = false;
        return result;
    }

    public void addExpressionLanguage(String name, ExpressionParser parser) {
        if (immutable)
            throw new IllegalStateException("controller is immutable");
        if (name.length() == 0)
            throw new IllegalArgumentException("name is empty");
        if (languagesByName.containsKey(name))
            throw new IllegalArgumentException("expression language '" + name
                                               + "' already exists");
        languagesByName.put(name, parser);
    }

    public void replaceExpressionLanguage(String name, ExpressionParser parser)
    {
        if (immutable)
            throw new IllegalStateException("controller is immutable");
        if (name.length() == 0)
            throw new IllegalArgumentException("name is empty");
        languagesByName.put(name, parser);
    }

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    public String[] expressionLanguageNames() {
        Set names = languagesByName.keySet();
        return (String[]) names.toArray(EMPTY_STRING_ARRAY);
    }

    public ExpressionParser findByName(String name)
        throws ExpressionParseException
    {
        ExpressionParser parser = (ExpressionParser) languagesByName.get(name);

        if (parser == null)
            throw new ExpressionParseException("expression language '" + name
                                               + "' does not exist");
        return parser;
    }

    /**
     * By convention, expressions passed to the {@link #parse(String)} and
     * {@link #evaluate(String, Map)} methods are parsed in the first
     * instance by the {@link ExpressionParser} with the name
     * <code>COMPOUND</code>.
     */
    public static final String COMPOUND = "compound";

    public static final String STANDARD = "standard";

    public static final ParseController DEFAULT = new ParseController();

    static {
        DEFAULT.addExpressionLanguage(STANDARD,
                                      new ContextValueExpressionParser());
        BracketedExpressionParser compoundParser
                = new BracketedExpressionParser('{', '}');
        compoundParser.addExpressionParser('$', STANDARD);
        DEFAULT.addExpressionLanguage(COMPOUND, compoundParser);
    }

    public Expression parse(String expression, String dialect)
        throws ExpressionParseException
    {
        return findByName(dialect).parse(expression, this);
    }

    public Object evaluate(String expression, String dialect, Map context)
        throws ExpressionParseException, ExpressionEvaluationException
    {
        return parse(expression, dialect).evaluate(context);
    }

    public Expression parse(String expression)
        throws ExpressionParseException
    {
        return findByName(COMPOUND).parse(expression, this);
    }

    public String evaluate(String text, Map context)
        throws ExpressionParseException, ExpressionEvaluationException
    {
        return (String) parse(text).evaluate(context);
    }
}
