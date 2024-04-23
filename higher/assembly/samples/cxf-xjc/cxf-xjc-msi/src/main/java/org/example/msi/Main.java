package org.example.msi;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 * Example of unmarshalling a Message Status Identifier
 */
public class Main
{
	public static final String SAMPLE_MSI_FILE = "src/test/samples/msi.xml";
	
	private static final org.example.msi.ObjectFactory OF = new org.example.msi.ObjectFactory();
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	public static Logger getLogger() { return logger; }
	
	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() throws JAXBException
	{
		if ( jaxbContext == null )
			setJaxbContext(JAXBContext.newInstance(OF.getClass()));
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

	// Represents the unmarshalled Message.
	private Message msi = null;
	public Message getMessage() { return msi; }
	public void setMessage(Message msi) { this.msi = msi; }

	/**
	 * Command Line Invocation.
	 * @param args CLI argument(s)
	 */
	public static void main(String[] args) throws Exception
	{
		String path = args.length > 0 ? args[0] : SAMPLE_MSI_FILE;
		Main main = new Main();
		main.execute(path);
	}
	
	private void execute(String path) throws JAXBException
	{
		File xmlFile = new File(path);
		final Object object = getUnmarshaller().unmarshal(xmlFile);
		if ( object instanceof Message )
		{
	        setMessage((Message) object);
			println("Message: " + getMessage().getId() + "\n\n" + getMessage() + "\n");
		}
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
