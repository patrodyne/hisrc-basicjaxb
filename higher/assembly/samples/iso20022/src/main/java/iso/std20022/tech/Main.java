package iso.std20022.tech;

import static jakarta.xml.bind.JAXBIntrospector.getValue;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import iso.std20022.tech.pain_013_001_10.CreditorPaymentActivationRequest;
import iso.std20022.tech.pain_013_001_10.Document;
import iso.std20022.tech.pain_013_001_10.ObjectFactory;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 * Example of unmarshalling a ISO 20022 Document
 */
public class Main
{
	public static final String SAMPLE_ISO20022_FILE = "src/test/samples/pain01.xml";
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	public static Logger getLogger() { return logger; }
	
	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() throws JAXBException
	{
		if ( jaxbContext == null )
			setJaxbContext(JAXBContext.newInstance(ObjectFactory.class));
		return jaxbContext;
	}
	public void setJaxbContext(JAXBContext jaxbContext)
	{
		this.jaxbContext = jaxbContext;
	}

	private Unmarshaller unmarshaller = null;
	protected Unmarshaller getUnmarshaller() throws JAXBException
	{
		if ( unmarshaller == null )
			setUnmarshaller(getJaxbContext().createUnmarshaller());
		return unmarshaller;
	}
	protected void setUnmarshaller(Unmarshaller unmarshaller)
	{
		this.unmarshaller = unmarshaller;
	}

	// Represents the unmarshalled Document.
	private Document document = null;
	public Document getDocument() { return document; }
	public void setDocument(Document document) { this.document = document; }

	/**
	 * Command Line Invocation.
	 * @param args CLI argument(s)
	 */
	public static void main(String[] args) throws Exception
	{
		String path = args.length > 0 ? args[0] : SAMPLE_ISO20022_FILE;
		Main main = new Main();
		main.execute(path);
	}
	
	private void execute(String path) throws JAXBException
	{
		File xmlFile = new File(path);
        setDocument((Document) getValue(getUnmarshaller().unmarshal(xmlFile)));
        CreditorPaymentActivationRequest cpar = getDocument().getCreditorPaymentActivationRequest();
        String msgId = cpar.getMessageIdentification();
		println("Document: " + msgId + "\n\n" + cpar + "\n");
	}

	/**
	 * Print a text string.
	 * @param text The text to be printed.
	 */
    public static void println(String text)
	{
		getLogger().info(text);
	}
}
// vi:set tabstop=4 hardtabs=4 shiftwidth=4:
