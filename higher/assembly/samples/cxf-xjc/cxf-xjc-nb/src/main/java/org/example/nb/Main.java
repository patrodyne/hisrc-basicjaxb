package org.example.nb;

import java.io.File;

import org.example.nb.A.NotebookAnalog;
import org.example.nb.B.NotebookDigital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 * Example of unmarshalling a NotebookAnalog
 */
public class Main
{
	public static final String SAMPLE_NB_FILE = "src/test/samples/nbA.xml";
	
	private static final org.example.nb.A.ObjectFactory OFA = new org.example.nb.A.ObjectFactory();
	private static final org.example.nb.B.ObjectFactory OFB = new org.example.nb.B.ObjectFactory();
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	public static Logger getLogger() { return logger; }
	
	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() throws JAXBException
	{
		if ( jaxbContext == null )
			setJaxbContext(JAXBContext.newInstance(OFA.getClass(), OFB.getClass() ));
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

	// Represents the unmarshalled NotebookAnalog.
	private NotebookAnalog nba = null;
	public NotebookAnalog getNotebookAnalog() { return nba; }
	public void setNotebookAnalog(NotebookAnalog nb) { this.nba = nb; }

	// Represents the unmarshalled NotebookDigital.
	private NotebookDigital nbb = null;
	public NotebookDigital getNotebookDigital() { return nbb; }
	public void setNotebookDigital(NotebookDigital nbb) { this.nbb = nbb; }

	/**
	 * Command Line Invocation.
	 * @param args CLI argument(s)
	 */
	public static void main(String[] args) throws Exception
	{
		String path = args.length > 0 ? args[0] : SAMPLE_NB_FILE;
		Main main = new Main();
		main.execute(path);
	}
	
	private void execute(String path) throws JAXBException
	{
		File xmlFile = new File(path);
		final Object object = getUnmarshaller().unmarshal(xmlFile);
		if ( object instanceof NotebookAnalog )
		{
	        setNotebookAnalog((NotebookAnalog) object);
			println("NotebookAnalog: " + getNotebookAnalog().getTitle() + "\n\n" + getNotebookAnalog() + "\n");
		}
		else if ( object instanceof NotebookDigital )
		{
	        setNotebookDigital((NotebookDigital) object);
			println("NotebookDigital: " + getNotebookDigital().getTitle() + "\n\n" + getNotebookDigital() + "\n");
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
