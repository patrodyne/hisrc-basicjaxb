package org.example.modulo;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;

import org.example.modulo.pgr.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * Example of unmarshalling a Message Status Identifier
 */
public class Main
{
	public static final String SAMPLE_MODULO_FILE = "src/test/samples/pgr1.xml";
	
	//private static final org.example.modulo.ia.ObjectFactory OFIA = new org.example.modulo.ia.ObjectFactory();
	//private static final org.example.modulo.pa.ObjectFactory OFPA = new org.example.modulo.pa.ObjectFactory();
	private static final org.example.modulo.pgr.ObjectFactory OFPGR = new org.example.modulo.pgr.ObjectFactory();
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	public static Logger getLogger() { return logger; }
	
	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() throws JAXBException
	{
		if ( jaxbContext == null )
			setJaxbContext(JAXBContext.newInstance(OFPGR.getClass()));
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


	private Marshaller marshaller = null;
	protected Marshaller getMarshaller() throws JAXBException
	{
		if ( marshaller == null )
		{
			marshaller = getJaxbContext().createMarshaller();
			marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
		}
		return marshaller;
	}
	
	// Represents the unmarshalled Root.
	private Root root = null;
	public Root getRoot() { return root; }
	public void setRoot(Root root) { this.root = root; }

	/**
	 * Command Line Invocation.
	 * @param args CLI argument(s)
	 */
	public static void main(String[] args) throws Exception
	{
		String path = args.length > 0 ? args[0] : SAMPLE_MODULO_FILE;
		Main main = new Main();
		main.execute(path);
	}
	
	@SuppressWarnings("unchecked")
	private void execute(String path) throws JAXBException, IOException
	{
		File xmlFile = new File(path);
		final Object object = getUnmarshaller().unmarshal(xmlFile);
		if ( object instanceof JAXBElement )
		{
	        setRoot(((JAXBElement<Root>) object).getValue());
	        for ( JAXBElement<? extends Serializable> content : getRoot().getContent() )
	        {
	        	Serializable value = content.getValue();
	        	if ( value instanceof org.example.modulo.ia.Modulo )
	        	{
	        		org.example.modulo.ia.Modulo moduloIA = (org.example.modulo.ia.Modulo) value;
	    			println("Root Content: modulo (IA)=\n" + moduloIA + "\n");
	        	}
	        	else if ( value instanceof org.example.modulo.pa.Modulo )
	        	{
	        		org.example.modulo.pa.Modulo moduloPA = (org.example.modulo.pa.Modulo) value;
	    			println("Root Content: modulo (PA)=\n" + moduloPA+ "\n");
	        	}
	        	else
	        	{
	        		String frontespizioPostel = (String) value;
	    			println("Root Content: frontespizioPostel=\n" + frontespizioPostel+ "\n");
	        	}
	        }
	        JAXBElement<Root> rootJE = OFPGR.createRoot(getRoot());
			println("Root:\n" + marshalToString(rootJE));
		}
	}

    protected String marshalToString(Object instance) throws JAXBException, IOException
    {
        try ( StringWriter writer = new StringWriter() )
        {
            getMarshaller().marshal(instance, writer);
            return writer.toString();
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
