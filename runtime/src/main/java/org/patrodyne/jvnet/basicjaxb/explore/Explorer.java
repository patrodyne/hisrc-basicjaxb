package org.patrodyne.jvnet.basicjaxb.explore;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
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

/**
 * A Swing application to explore features of the HiSrc BasicJAXB Runtime library.
 * 
 * Projects can create their own custom Explorer by extending AbstractExplorer then
 * provide a HTML lesson page and adding JMenuItem(s) to trigger exploratory code.
 * 
 * The AbstractExplorer displays three panels: a HTML lesson, a print console and an
 * error console. The lesson file is read as a resource relative to this class. Text
 * is sent to the print console by calling 'println(text)' and error messages are
 * sent to the error console by calling 'errorln(msg)'. Also, 'standard out' and
 * 'standard error' streams are sent to the respective consoles.
 * 
 * @author Rick O'Sullivan
 */
@SuppressWarnings("serial")
public class Explorer extends AbstractExplorer
{
	private static final String WINDOW_TITLE = "HiSrc BasicJAXB Runtime Library";
	private static final String EXPLORER_HTML = "Explorer.html";

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
		setJMenuBar(createMenuBar());
		try
		{
			initializeExplorer();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() { return jaxbContext; }
	public void setJaxbContext(JAXBContext jaxbContext) { this.jaxbContext = jaxbContext; }

	private Marshaller marshaller;
	public Marshaller getMarshaller() { return marshaller; }
	public void setMarshaller(Marshaller marshaller) { this.marshaller = marshaller; }

	private Unmarshaller unmarshaller;
	public Unmarshaller getUnmarshaller() { return unmarshaller; }
	public void setUnmarshaller(Unmarshaller unmarshaller) { this.unmarshaller = unmarshaller; }

	private void initializeExplorer() throws Exception
	{
		setJaxbContext(createJAXBContext());
		setMarshaller(createMarshaller(getJaxbContext()));
		setUnmarshaller(createUnmarshaller(getJaxbContext()));
		
		SchemaOutputStringResolver sosr = new SchemaOutputStringResolver();
		getJaxbContext().generateSchema(sosr);
		println("SchemaString:\n\n" + sosr.getSchemaString());

		SchemaOutputDomResolver sodr = new SchemaOutputDomResolver();
		getJaxbContext().generateSchema(sodr);
		println("SchemaNodeString:\n\n" + sodr.getSchemaNodeString());
		
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
        final Schema schema = schemaFactory.newSchema(sodr.getDomSource());
		println("Schema: " + schema);
		
		getMarshaller().setSchema(schema);
		getUnmarshaller().setSchema(schema);

		Contact contact1 = new Contact("Arthur", "Dent");
		String contact1Xml = marshalToString(contact1);
		println("contact1Xml:\n\n" + contact1Xml);

		ObjectLocator contact1Locator = new DefaultRootObjectLocator(contact1);

		HashCodeStrategy2 hashCodeStrategy = JAXBHashCodeStrategy.getInstance();
		
		int contact1HashCode = hashCodeStrategy.hashCode(contact1Locator, 1, contact1, (contact1 != null));
		println("Contact1 hashCode: " + contact1HashCode);
	
		Contact contact2 = unmarshalFromString(contact1Xml);
//		Contact contact2 = unmarshalFromString(contact1Xml.replaceAll("firstName", "babelFish"));
		contact2.firstName = "Ford";

		ObjectLocator contact2Locator = new DefaultRootObjectLocator(contact2);

		int contact2HashCode = hashCodeStrategy.hashCode(contact2Locator, 1, contact2, (contact2 != null));
		println("Contact2 hashCode: " + contact2HashCode);
		
		EqualsStrategy2 equalsStrategy = JAXBEqualsStrategy.getInstance();
		
		boolean contact1_eq_contact2 = equalsStrategy.equals
		(
			contact1Locator, contact2Locator, 
			contact1, contact2, 
			(contact1 != null), (contact2 != null)
		);
		println("Contact1 == Contact2: " + contact1_eq_contact2);
		
		println("contact1Locator"+contact1Locator.getPathAsString());
		println("contact2Locator"+contact2Locator.getPathAsString());
		
	}
	
	private JAXBContext createJAXBContext() throws JAXBException
	{
		return JAXBContext.newInstance(Contact.class);
	}

	private Marshaller createMarshaller(JAXBContext jaxbContext) throws JAXBException
	{
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
		return marshaller;
	}
	
	private Unmarshaller createUnmarshaller(JAXBContext jaxbContext) throws JAXBException
	{
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//		unmarshaller.setSchema(null);
		return unmarshaller;
	}

	/*
	 * Create a JMenuBar to display JMenuItem(s) to trigger
	 * your own exploratory methods.
	 */
	private JMenuBar createMenuBar()
	{
		return new JMenuBar();
	}
	
	private String marshalToString(Object instance) throws JAXBException, IOException
	{
		try ( StringWriter writer = new StringWriter() )
		{
			getMarshaller().marshal(instance, writer);
			return writer.toString();
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T unmarshalFromString(String xml) throws JAXBException
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
	protected static class Contact implements HashCode2, Equals2
	{
		public String firstName;
		public String lastName;
		
		public Contact(String firstName, String lastName)
		{
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public Contact()
		{
			this("firstName", "lastName");
		}

		@Override
		public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy)
		{
			int currentHashCode = 1;
	        {
	            String theLastName;
	            theLastName = this.firstName;
	            ObjectLocator theLastNameLocator = LocatorUtils.property(locator, "firstName", theLastName);
	            currentHashCode = strategy.hashCode(theLastNameLocator, currentHashCode, theLastName, (this.firstName != null));
	        }
	        {
	            String theLastName;
	            theLastName = this.lastName;
	            ObjectLocator theLastNameLocator = LocatorUtils.property(locator, "lastName", theLastName);
	            currentHashCode = strategy.hashCode(theLastNameLocator, currentHashCode, theLastName, (this.lastName != null));
	        }
	        return currentHashCode;
		}
		
	    @Override
	    public int hashCode()
	    {
	        return this.hashCode(null, JAXBHashCodeStrategy.getInstance());
	    }

	    @Override
	    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy)
	    {
	        if ( (object == null) || (this.getClass() != object.getClass()) )
	            return false;
	        if ( this == object )
	            return true;
	        final Contact that = ((Contact) object);
	        // firstName
	        {
	        	String lhsLastName = this.firstName;
	        	String rhsLastName = that.firstName;
	        	ObjectLocator lhsLastNameLocator = LocatorUtils.property(thisLocator, "firstName", lhsLastName);
	        	ObjectLocator rhsLastNameLocator = LocatorUtils.property(thatLocator, "firstName", rhsLastName);
	        	boolean lhsLastNameNotNull = (lhsLastName != null);
	        	boolean rhsLastNameNotNull = (rhsLastName != null);
	        	if ( !strategy.equals(
	        			lhsLastNameLocator, rhsLastNameLocator,
	        			lhsLastName, rhsLastName,
	        			lhsLastNameNotNull, rhsLastNameNotNull) )
	        		return false;
	        }
	        // lastName
	        {
	        	String lhsLastName = this.lastName;
	        	String rhsLastName = that.lastName;
	        	ObjectLocator lhsLastNameLocator = LocatorUtils.property(thisLocator, "lastName", lhsLastName);
	        	ObjectLocator rhsLastNameLocator = LocatorUtils.property(thatLocator, "lastName", rhsLastName);
	        	boolean lhsLastNameNotNull = (lhsLastName != null);
	        	boolean rhsLastNameNotNull = (rhsLastName != null);
	        	if ( !strategy.equals(
	        			lhsLastNameLocator, rhsLastNameLocator,
	        			lhsLastName, rhsLastName,
	        			lhsLastNameNotNull, rhsLastNameNotNull) )
	        		return false;
	        }
	        return true;
	    }
	    
	    @Override
	    public boolean equals(Object object)
	    {
	        return equals(null, null, object, JAXBEqualsStrategy.getInstance());
	    }
	}
}
