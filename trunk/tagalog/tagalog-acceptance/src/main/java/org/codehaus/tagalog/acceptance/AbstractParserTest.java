/*
 * $Id: AbstractParserTest.java,v 1.21 2005-05-18 14:21:13 krisb Exp $
 */

package org.codehaus.tagalog.acceptance;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.codehaus.tagalog.ParseError;
import org.codehaus.tagalog.ParserConfiguration;
import org.codehaus.tagalog.TagError;
import org.codehaus.tagalog.TagalogParseException;
import org.codehaus.tagalog.TagalogParser;
import org.codehaus.tagalog.acceptance.people.People;
import org.codehaus.tagalog.acceptance.people.PeopleTagLibrary;
import org.codehaus.tagalog.acceptance.people.Person;
import org.xml.sax.SAXParseException;

/**
 * Abstract base class providing XML parsing tests. Subclasses are responsible
 * for connecting these tests to a concrete parser instance.
 *
 * @author <a href="mailto:mhw@kremvax.net">Mark Wilkinson</a>
 * @version $Revision: 1.21 $
 */
public abstract class AbstractParserTest extends TestCase {

    protected abstract TagalogParser createParser(URL testSource,
            ParserConfiguration configuration) throws Exception;

    private ParserConfiguration peopleConfiguration;

    protected void setUp() throws Exception {
        super.setUp();

        peopleConfiguration = new ParserConfiguration();
        peopleConfiguration.addTagLibrary(PeopleTagLibrary.NS_URI,
                                          new PeopleTagLibrary());
    }

    private URL getResource(String name) {
        return AbstractParserTest.class.getResource(name);
    }

    private void checkPeople(People people) {
        List personList;
        Person person;
        personList = people.getPeople();
        assertEquals(2, personList.size());
        person = (Person) personList.get(0);
        assertEquals("Mark", person.getFirstName());
        assertEquals("Wilkinson", person.getLastName());
        assertEquals("mhw", person.getUserId());
        person = (Person) personList.get(1);
        assertEquals("Bob", person.getFirstName());
        assertEquals("McWhirter", person.getLastName());
        assertEquals("bob", person.getUserId());
    }

    public void testParsePeople() throws Exception {
        URL peopleXml = getResource("people.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertEquals(0, p.parseErrors().length);
        checkPeople(people);
    }

    public void testParsePeopleExplicitNamespace() throws Exception {
        URL peopleXml = getResource("people-ns.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertEquals(0, p.parseErrors().length);
        checkPeople(people);
    }

    public void testParsePeopleNoNamespace() throws Exception {
        URL peopleXml = getResource("people-no-ns.xml");
        peopleConfiguration.setDefaultNamespace("tagalog:people");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertEquals(0, p.parseErrors().length);
        checkPeople(people);
    }

    public void testParsePeopleNoNamespaceNoDefault() throws Exception {
        URL peopleXml = getResource("people-no-ns.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertNull(people);
        ParseError[] errors = p.parseErrors();
        assertEquals(1, errors.length);
        assertEquals(3, errors[0].getLocation().getLine());
        assertEquals("no tag library for elements with no namespace",
                     errors[0].getMessage());
    }

    /*
     * Parsing a file with a namespace we don't have a tag library for
     * should return an error.
     */
    public void testParsePeopleBadNamespace() throws Exception {
        URL peopleXml = getResource("people-bad-ns.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertNull(people);
        ParseError[] errors = p.parseErrors();
        assertEquals(1, errors.length);
        assertEquals(3, errors[0].getLocation().getLine());
        assertEquals("no tag library for namespace 'bogus:namespace'",
                     errors[0].getMessage());
    }

    /*
     * Parsing a file with a tag that we don't recognise should return an
     * error, but the content should still be returned ok.
     */
    public void testParsePeopleBadTag() throws Exception {
        URL peopleXml = getResource("people-bad-tag.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertNotNull(people);
        ParseError[] errors = p.parseErrors();
        assertEquals(1, errors.length);
        assertEquals(8, errors[0].getLocation().getLine());
        assertEquals("no tag 'username' in tag library"
                     + " for namespace 'tagalog:people'",
                     errors[0].getMessage());
    }

    /*
     * Tags might throw exceptions if they have bugs in them; we should
     * preserve all the exception detail.
     */
    public void testParsePeopleBrokenTag() throws Exception {
        URL peopleXml = getResource("people-broken-tag.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);

        try {
            p.parse();
            fail("should have thrown NullPointerException");
        } catch (TagalogParseException e) {
            Throwable cause1 = e.getCause();
            assertTrue(cause1 instanceof SAXParseException);
            Exception cause2 = ((SAXParseException) cause1).getException();
            assertTrue(cause2 instanceof NullPointerException);
            assertEquals("from BrokenTag", cause2.getMessage());
        } catch (NullPointerException e) {
            assertEquals("from BrokenTag", e.getMessage());
        }
    }

    private void checkPeopleComments(People people) {
        List personList;
        Person person;
        personList = people.getPeople();
        assertEquals(2, personList.size());
        person = (Person) personList.get(0);
        assertEquals("Tagalog Developer", person.getComment());
        person = (Person) personList.get(1);
        assertEquals("Codehaus Despot", person.getComment());
    }

    /*
     * Make sure comments without nested markup work Ok.
     */
    public void testParsePeopleWithFlatComments() throws Exception {
        URL peopleXml = getResource("people-comment.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertEquals(0, p.parseErrors().length);
        checkPeople(people);
        checkPeopleComments(people);
    }

    /*
     * Make sure comments with nested markup are Ok too.
     */
    public void testParsePeopleWithTreeComments() throws Exception {
        URL peopleXml = getResource("people-comment-tree.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertEquals(0, p.parseErrors().length);
        checkPeople(people);
        checkPeopleComments(people);
    }

    /*
     * Make sure nested markup is rejected when StringTag is used.
     */
    public void testParsePeopleWithTreeNames() throws Exception {
        URL peopleXml = getResource("people-name-tree.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertNotNull(people);
        ParseError[] errors = p.parseErrors();
        assertEquals(1, errors.length);
        assertEquals(5, errors[0].getLocation().getLine());
        assertEquals("<first-name> cannot contain markup",
                     errors[0].getMessage());
    }

    private void checkPeopleEntities(People people) {
        List personList = people.getPeople();
        assertEquals(4, personList.size());
        Person person = (Person) personList.get(0);
        assertEquals("ampersand", person.getUserId());
        assertEquals("name with & & ampersands", person.getFirstName());
        assertEquals("name with & & ampersands", person.getLastName());
        person = (Person) personList.get(1);
        assertEquals("angles", person.getUserId());
        assertEquals("name with < > angles", person.getFirstName());
        assertEquals("name with < > angles", person.getLastName());
        person = (Person) personList.get(2);
        assertEquals("quotes", person.getUserId());
        assertEquals("name with ' \" quotes", person.getFirstName());
        assertEquals("name with ' \" quotes", person.getLastName());
        person = (Person) personList.get(3);
        assertEquals("cdata", person.getUserId());
        assertEquals("name with <cdata>&<section> cdata",
                     person.getFirstName());
        assertEquals("name with <cdata>&<section> cdata",
                     person.getLastName());
    }

    /*
     * Parsers might convert the different XML quoting mechanisms into
     * separate events delivered to the tags. Make sure the content gets
     * through.
     */
    public void testParsePeopleQuoting() throws Exception {
        URL peopleXml = getResource("people-quoting.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        checkPeopleEntities(people);
    }

    /*
     * The XmlPullParser uses a different event-delivery mecahnism when
     * processing instructions are being processed. Check this case too.
     */
    public void testParsePeopleQuotingWithPIHandler() throws Exception {
        URL peopleXml = getResource("people-quoting.xml");
        peopleConfiguration.setProcessingInstructionTagLibrary(
                new RecordMostRecentPITagLibrary());
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        checkPeopleEntities(people);
    }

    /*
     * XML documents can contain processing instructions. Check that
     * they are ignored normally.
     */
    public void testParsePeopleWithNoProcessingInstructionHandler()
        throws Exception
    {
        URL peopleXml = getResource("people-pi1.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        HashMap map = new HashMap();
        People people = (People) p.parse(map);
        assertNotNull(people);

        assertNull(map.get(AbstractPIHandler.PI_CONTEXT_KEY));
        assertNull(map.get("foo-pi"));
        assertNull(map.get("bar-pi"));
    }

    /*
     * Check that processing instructions are handed off to
     * {@link RecordMostRecentPIHandler} properly.
     */
    public void testParsePeopleWithNormalProcessingInstructionHandler()
        throws Exception
    {
        URL peopleXml = getResource("people-pi1.xml");
        peopleConfiguration.setProcessingInstructionTagLibrary(
                new RecordMostRecentPITagLibrary());
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        HashMap map = new HashMap();
        People people = (People) p.parse(map);
        assertNotNull(people);

        Map piContext = (Map) map.get(AbstractPIHandler.PI_CONTEXT_KEY);
        String data = (String) piContext.get("foo-pi");
        assertEquals("second value", data);
        data = (String) piContext.get("bar-pi");
        assertEquals("hello", data);
    }

    /*
     * Check that processing instructions handed off to
     * {@link RecordAllPIHandler} are processed correctly.
     */
    public void testParsePeopleWithProcessingInstructionMapOfListsHandler()
        throws Exception
    {
        URL peopleXml = getResource("people-pi1.xml");
        peopleConfiguration.setProcessingInstructionTagLibrary(
                new RecordAllPITagLibrary());
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        HashMap map = new HashMap();
        People people = (People) p.parse(map);
        assertNotNull(people);

        Map piContext = (Map) map.get(AbstractPIHandler.PI_CONTEXT_KEY);
        List piList = (List) piContext.get("foo-pi");
        assertEquals(2, piList.size());
        assertEquals("value bar", piList.get(0));
        assertEquals("second value", piList.get(1));
        piList = (List) piContext.get("bar-pi");
        assertEquals(1, piList.size());
        assertEquals("hello", piList.get(0));
    }

    /*
     * Check that {@link TagException} is reported as an error and the cause is
     * available
     */
    public void testParsePeopleWithCause() throws Exception {
        URL peopleXml = getResource("people-cause-tag.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        People people = (People) p.parse();
        assertNotNull(people);
        ParseError[] errors = p.parseErrors();
        assertEquals(1, errors.length);
        assertEquals(8, errors[0].getLocation().getLine());
        assertEquals("tag exception with cause", errors[0].getMessage());
        Throwable cause = errors[0].getCause();
        assertTrue(cause instanceof IllegalStateException);
        assertEquals("invalid state", cause.getMessage());
    }

    /*
     * Check that {@link TagError} is wrapped and rethrown as a
     * {@link TagalogParseException}.
     */
    public void testParsePeopleWithTagError() throws Exception {
        URL peopleXml = getResource("people-tag-error.xml");
        TagalogParser p = createParser(peopleXml, peopleConfiguration);
        try {
            p.parse();
            fail("should have thrown TagError");
        } catch (TagalogParseException e) {
            Throwable cause = e.getCause();
            assertTrue(cause instanceof TagError);
            assertEquals("tag error", cause.getMessage());
        }
    }

}
