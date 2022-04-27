package org.patrodyne.jvnet.basicjaxb.explore;

import static java.lang.Integer.toHexString;
import static java.lang.System.identityHashCode;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.TransformerException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jvnet.jaxb2_commons.lang.Equals2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy2;
import org.jvnet.jaxb2_commons.lang.HashCode2;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy2;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputDomResolver;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputStringResolver;
import org.xml.sax.SAXException;

/**
 * A Swing application to explore features of the HiSrc BasicJAXB Runtime library.
 * 
 * Projects create their own custom Explorer by extending AbstractExplorer and
 * providing an HTML lesson page then adding JMenuItem(s) to trigger exploratory code.
 * 
 * The AbstractExplorer displays three panels: an HTML lesson, a print console and an
 * error console. The lesson file is read as a resource relative to this class. Text
 * is sent to the print console by calling 'println(text)' and error messages are
 * sent to the error console by calling 'errorln(msg)'. Also, 'standard out' /
 * 'standard error' streams are sent to their respective consoles.
 * 
 * @author Rick O'Sullivan
 */
@SuppressWarnings("serial")
public class Explorer extends AbstractExplorer
{
	private static final String WINDOW_TITLE = "HiSrc BasicJAXB Runtime Library";
	private static final String EXPLORER_HTML = "Explorer.html";

	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() { return jaxbContext; }
	public void setJaxbContext(JAXBContext jaxbContext) { this.jaxbContext = jaxbContext; }

	private Marshaller marshaller;
	public Marshaller getMarshaller() { return marshaller; }
	public void setMarshaller(Marshaller marshaller) { this.marshaller = marshaller; }

	private Unmarshaller unmarshaller;
	public Unmarshaller getUnmarshaller() { return unmarshaller; }
	public void setUnmarshaller(Unmarshaller unmarshaller) { this.unmarshaller = unmarshaller; }
	
	private Person person1;
	public Person getPerson1() { return person1; }
	public void setPerson1(Person person1) { this.person1 = person1; }

	private Person person2;
	public Person getPerson2() { return person2; }
	public void setPerson2(Person person2) { this.person2 = person2; }

	private Person person3;
	public Person getPerson3() { return person3; }
	public void setPerson3(Person person3) { this.person3 = person3; }

	/**
	 * Main entry point for command line invocation.
	 * @param args CLI arguments
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			JFrame frame = new Explorer(EXPLORER_HTML);
			frame.setVisible(true);
		});
	}

	/**
	 * Construct application with an HTML lesson.
	 */
	public Explorer(String htmlName)
	{
		super(htmlName);
		setTitle(WINDOW_TITLE);
		try
		{
			initializeExplorer();
			setJMenuBar(createMenuBar());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void generateXmlSchemaFromString()
	{
		try
		{
			SchemaOutputStringResolver sosr = new SchemaOutputStringResolver();
			getJaxbContext().generateSchema(sosr);
			println("Xml Schema from String:\n\n" + sosr.getSchemaString());
		}
		catch ( IOException ex )
		{
			errorln(ex);
		}
	}
	
	public void generateXmlSchemaFromDom()
	{
		try
		{
			SchemaOutputDomResolver sodr = new SchemaOutputDomResolver();
			getJaxbContext().generateSchema(sodr);
			println("Xml Schema from DOM:\n\n" + sodr.getSchemaDomNodeString());
		}
		catch ( IOException | TransformerException ex )
		{
			errorln(ex);
		}
	}
	
	public void generateXmlSchemaValidatorFromDom()
	{
		try
		{
			// Generate a Schema Validator from given the JAXB context.
			SchemaOutputDomResolver sodr = new SchemaOutputDomResolver();
			getJaxbContext().generateSchema(sodr);
			SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
			Schema schemaValidator = schemaFactory.newSchema(sodr.getDomSource());
			
			// Configure Marshaller / unmarshaller to use validator.
			getMarshaller().setSchema(schemaValidator);
			getUnmarshaller().setSchema(schemaValidator);
			
			println("Schema Validator:\n\n" + schemaValidator);
			println();
		}
		catch ( IOException | SAXException ex )
		{
			errorln(ex);
		}
	}
	
	public void marshalPerson1()
	{
		marshal("Person1", getPerson1());
	}
	
	public void marshalPerson2()
	{
		marshal("Person2", getPerson2());
	}
	
	public void marshalPerson3()
	{
		marshal("Person3", getPerson3());
	}

	private void marshal(String label, Person person)
	{
		try
		{
			String phc = toHexString(person.hashCode());
			String ihc = toHexString(identityHashCode(person));
			String personXml = marshalToString(person);
			println(label + " XML: (P#=" + phc + ", O#=" + ihc + ")\n\n" +personXml);
			println();
		}
		catch (JAXBException | IOException ex)
		{
			errorln(ex);
		}
	}
	
	public void compareHashCodes()
	{
		String person1HashCode = toHexString(getPerson1().hashCode());
		String person2HashCode = toHexString(getPerson2().hashCode());
		String person3HashCode = toHexString(getPerson3().hashCode());

		String person1IdentityHashCode = toHexString(identityHashCode(getPerson1()));
		String person2IdentityHashCode = toHexString(identityHashCode(getPerson2()));
		String person3IdentityHashCode = toHexString(identityHashCode(getPerson3()));
		
		println("Compare Hash Codes\n");
		println("Person1 hashCode: " + person1HashCode + "; identityHashCode: " + person1IdentityHashCode);
		println("Person2 hashCode: " + person2HashCode + "; identityHashCode: " + person2IdentityHashCode);
		println("Person3 hashCode: " + person3HashCode + "; identityHashCode: " + person3IdentityHashCode);
		println();
	}
	
	public void compareEquality()
	{
		println("Compare Equality\n");
		println("Person1 vs Person2: " + (getPerson1().equals(getPerson2()) ? "EQUAL" : "UNEQUAL"));
		println("Person1 vs Person3: " + (getPerson1().equals(getPerson3()) ? "EQUAL" : "UNEQUAL"));
		println();
	}

	public void roundtripValid()
	{
		try
		{
			Person person1A = getPerson1();
			String person1AXml = marshalToString(person1A);
			Person person1B = unmarshalFromString(person1AXml);
			println("Person1A vs Person1B: " + (person1A.equals(person1B) ? "EQUAL" : "UNEQUAL"));
			println();
		}
		catch (JAXBException | IOException ex)
		{
			errorln(ex);
		}
	}

	public void roundtripInvalid()
	{
		try
		{
			// person1BXml is intentionally invalid!
			Person person1A = getPerson1();
			String person1AXml = marshalToString(person1A);
			String person1BXml = person1AXml.replaceAll("lastName", "babelFish");
			Person person1B = unmarshalFromString(person1BXml);
			if ( person1A.equals(person1B) )
				println("Person1A vs Person1B: EQUAL");
			else
			{
				println("Person1A vs Person1B: UNEQUAL");
				println();
				println("[BEFORE] Person1A XML:\n" + person1AXml);
				println("[BEFORE] Person1B XML:\n" + person1BXml);
				println();
				println("[AFTER] Person1A XML:\n" + marshalToString(person1A));
				println("[AFTER] Person1B XML:\n" + marshalToString(person1B));
			}
			println();
		}
		catch (JAXBException | IOException ex)
		{
			errorln(ex);
		}
	}

	/**
	 * Dispatch hyperlinks from the lesson to local method invocations.
	 * The markdown for hyperlinks in the lesson is declared like this:
	 * 
	 *   [description](!hyperLink)
	 */
	@Override
	public void dispatchHyperLink(String hyperLink)
	{
		switch ( hyperLink )
		{
			case "generateXmlSchemaFromString": generateXmlSchemaFromString(); break;
			case "generateXmlSchemaFromDom": generateXmlSchemaFromDom(); break;
			case "generateXmlSchemaValidatorFromDom": generateXmlSchemaValidatorFromDom(); break;
			case "marshalPerson1": marshalPerson1(); break;
			case "marshalPerson2": marshalPerson2(); break;
			case "marshalPerson3": marshalPerson3(); break;
			case "compareHashCodes": compareHashCodes(); break;
			case "compareEquality": compareEquality(); break;
			case "roundtripValid": roundtripValid(); break;
			case "roundtripInvalid": roundtripInvalid(); break;
		}
	}

	/*
	 * Create a JMenuBar to display JMenuItem(s) to trigger
	 * your own exploratory methods.
	 */
	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		
		// Context Menu
		{
			JMenu contextMenu = new JMenu("Context");
			// Context: Generate XML Schema from String
			{
				JMenuItem menuItem = new JMenuItem("Generate XML Schema from String");
				menuItem.addActionListener((event) -> generateXmlSchemaFromString());
				contextMenu.add(menuItem);
			}
			// Context: Generate XML Schema from DOM
			{
				JMenuItem menuItem = new JMenuItem("Generate XML Schema from DOM");
				menuItem.addActionListener((event) -> generateXmlSchemaFromDom());
				contextMenu.add(menuItem);
			}
			// Context: Generate XML Schema Validator from DOM
			{
				JMenuItem menuItem = new JMenuItem("Generate XML Schema Validator from DOM");
				menuItem.addActionListener((event) -> generateXmlSchemaValidatorFromDom());
				contextMenu.add(menuItem);
			}
			menuBar.add(contextMenu);
		}

		// Marshal Menu
		{
			JMenu marshalMenu = new JMenu("Marshal");
			// Marshal: Person1
			{
				JMenuItem menuItem = new JMenuItem("Person1");
				menuItem.addActionListener((event) -> marshalPerson1());
				marshalMenu.add(menuItem);
			}
			// Marshal: Person2
			{
				JMenuItem menuItem = new JMenuItem("Person2");
				menuItem.addActionListener((event) -> marshalPerson2());
				marshalMenu.add(menuItem);
			}
			// Marshal: Person3
			{
				JMenuItem menuItem = new JMenuItem("Person3");
				menuItem.addActionListener((event) -> marshalPerson3());
				marshalMenu.add(menuItem);
			}
			menuBar.add(marshalMenu);
		}

		// Compare Menu
		{
			JMenu compareMenu = new JMenu("Compare");
			// Compare: HashCodes
			{
				JMenuItem menuItem = new JMenuItem("HashCodes");
				menuItem.addActionListener((event) -> compareHashCodes());
				compareMenu.add(menuItem);
			}
			// Compare: Equality
			{
				JMenuItem menuItem = new JMenuItem("Equality");
				menuItem.addActionListener((event) -> compareEquality());
				compareMenu.add(menuItem);
			}
			menuBar.add(compareMenu);
		}

		// Roundtrip Menu
		{
			JMenu roundtripMenu = new JMenu("Roundtrip");
			// Roundtrip: Valid
			{
				JMenuItem menuItem = new JMenuItem("Valid");
				menuItem.addActionListener((event) -> roundtripValid());
				roundtripMenu.add(menuItem);
			}
			// Roundtrip: Invalid
			{
				JMenuItem menuItem = new JMenuItem("Invalid");
				menuItem.addActionListener((event) -> roundtripInvalid());
				roundtripMenu.add(menuItem);
			}
			menuBar.add(roundtripMenu);
		}

		return menuBar;
	}
	
	private void initializeExplorer() throws Exception
	{
		setJaxbContext(createJAXBContext());
		setMarshaller(createMarshaller(getJaxbContext()));
		setUnmarshaller(createUnmarshaller(getJaxbContext()));
		setPerson1(new Person("Arthur", "Dent"));
		setPerson2(new Person("Arthur", "Dent"));
		setPerson3(new Person("Ford", "Prefect"));
	}

	private JAXBContext createJAXBContext() throws JAXBException
	{
		return JAXBContext.newInstance(Person.class);
	}

	protected Marshaller createMarshaller(JAXBContext jaxbContext) throws JAXBException
	{
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
		return marshaller;
	}
	
	protected Unmarshaller createUnmarshaller(JAXBContext jaxbContext) throws JAXBException
	{
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return unmarshaller;
	}

	protected String marshalToString(Object instance) throws JAXBException, IOException
	{
		try ( StringWriter writer = new StringWriter() )
		{
			getMarshaller().marshal(instance, writer);
			return writer.toString();
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> T unmarshalFromString(String xml) throws JAXBException
	{
		try ( StringReader reader = new StringReader(xml) )
		{
			Object instance = getUnmarshaller().unmarshal(reader);
			return (T) instance;
		}
	}
	
	/*
	 * Declare a simple class to demonstrate strategies provided by this library.
	 */
//	@XmlRootElement(namespace="https://www.example.org/hitchhiker/")
	@XmlRootElement
	public static class Person implements HashCode2, Equals2
	{
		public String firstName;
		public String lastName;
		
		public Person(String firstName, String lastName)
		{
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public Person()
		{
			this("firstName", "lastName");
		}

		@Override
		public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy)
		{
			int currentHashCode = 1;
			{
				String theFirstName = this.firstName;
				boolean theFirstNameIsSet = (theFirstName != null);
				ObjectLocator theFirstNameLocator = LocatorUtils.property(locator, "firstName", theFirstName);
				currentHashCode = strategy.hashCode(theFirstNameLocator, currentHashCode, theFirstName, theFirstNameIsSet);
			}
			{
				String theLastName = this.lastName;
				boolean theLastNameIsSet = (theLastName != null);
				ObjectLocator theLastNameLocator = LocatorUtils.property(locator, "lastName", theLastName);
				currentHashCode = strategy.hashCode(theLastNameLocator, currentHashCode, theLastName, theLastNameIsSet);
			}
			return currentHashCode;
		}
		
		@Override
		public int hashCode()
		{
			ObjectLocator thisLocator = new DefaultRootObjectLocator(this);
			HashCodeStrategy2 strategy = JAXBHashCodeStrategy.getInstance();
			return this.hashCode(thisLocator, strategy);
		}

		@Override
		public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy)
		{
			if ( (object == null) || (this.getClass() != object.getClass()) )
				return false;
			if ( this == object )
				return true;
			final Person that = ((Person) object);
			// firstName
			{
				String lhsFirstName = this.firstName;
				String rhsFirstName = that.firstName;
				ObjectLocator lhsFirstNameLocator = LocatorUtils.property(thisLocator, "firstName", lhsFirstName);
				ObjectLocator rhsFirstNameLocator = LocatorUtils.property(thatLocator, "firstName", rhsFirstName);
				boolean lhsFirstNameIsSet = (lhsFirstName != null);
				boolean rhsFirstNameIsSet = (rhsFirstName != null);
				if ( !strategy.equals(
						lhsFirstNameLocator, rhsFirstNameLocator,
						lhsFirstName, rhsFirstName,
						lhsFirstNameIsSet, rhsFirstNameIsSet) )
					return false;
			}
			// lastName
			{
				String lhsLastName = this.lastName;
				String rhsLastName = that.lastName;
				ObjectLocator lhsLastNameLocator = LocatorUtils.property(thisLocator, "lastName", lhsLastName);
				ObjectLocator rhsLastNameLocator = LocatorUtils.property(thatLocator, "lastName", rhsLastName);
				boolean lhsLastNameIsSet = (lhsLastName != null);
				boolean rhsLastNameIsSet = (rhsLastName != null);
				if ( !strategy.equals(
						lhsLastNameLocator, rhsLastNameLocator,
						lhsLastName, rhsLastName,
						lhsLastNameIsSet, rhsLastNameIsSet) )
					return false;
			}
			return true;
		}
		
		@Override
		public boolean equals(Object that)
		{
			// Create locators for enhanced debugging.
			ObjectLocator thisLocator = new DefaultRootObjectLocator(this);
			ObjectLocator thatLocator = new DefaultRootObjectLocator(that);
			
			// Log debug messages to standard output.
			JAXBEqualsStrategy strategy = new JAXBEqualsStrategy()
			{
				@Override
				public boolean isDebugEnabled() { return true; }
				
				@Override
				public void trace(String message)
				{
					System.out.println(message);
				}
			};
			
			// Return deep object equality.
			return equals(thisLocator, thatLocator, that, strategy);
		}
	}
}
