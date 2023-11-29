package org.example.custom.bio;

import static org.example.custom.bio.Context.HTML_RESUME_CLASS;
import static org.example.custom.bio.Context.XML_RESUME_CLASS;
import static org.example.custom.bio.Context.createMainMarshaller;
import static org.example.custom.bio.Context.createMainUnmarshaller;
import static org.jvnet.basicjaxb.dom.DOMUtils.buildToString;
import static org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.isJAXBElement;

import java.io.File;

import org.example.custom.bio.customer.Bio;
import org.example.custom.bio.customer.Customer;
import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * @see <a href="http://blog.bdoughan.com/2011/04/xmlanyelement-and-non-dom-properties.html">@XmlAnyElement and non-DOM Properties </a>
 */
public class Demo
{
	private static final String TAB = "  ";
	
	private static String sampleFileName = "src/test/samples/customer1.xml";
	public static String getSampleFileName() { return sampleFileName; }
	public static void setSampleFileName(String sampleFileName) { Demo.sampleFileName = sampleFileName; }

	public static void main(String[] args) throws Exception
	{
		if ( args.length == 1 )
			setSampleFileName(args[0]);
		
		Unmarshaller unmarshaller = createMainUnmarshaller();
		println("Unmarshal.....: " + sampleFileName);
		Customer customer = (Customer) unmarshaller.unmarshal(new File(getSampleFileName()));
		
		Bio bio = customer.getBio();
		Object bioAny = bio.getAny();
		
		println("Name..........: " + customer.getName());
		println("Bio Type......: " + bio.getType());
		println("Bio Any.......: " + bioAny);
		println("Bio Any Class.: " + bioAny.getClass());
		if ( isJAXBElement(bioAny) ) 
			println("Bio Any Value.: " + ((JAXBElement<?>) bioAny).getValue() );

		if ( bioAny != null )
		{
			if ( bioAny instanceof Element )
			{
				Element bioElement = (Element) bioAny;
				
				println("----------------------------------------");
				print(buildToString(TAB, bioElement.getOwnerDocument()));
				println("----------------------------------------");
				print(buildToString(TAB, bioElement));
				println("----------------------------------------");
			}
			else if ( HTML_RESUME_CLASS.equals(bioAny.getClass()) )
				println("HTML Name.....: " + HTML_RESUME_CLASS.cast(bioAny).getHtml().getBody().getH1());
			else if ( XML_RESUME_CLASS.equals(bioAny.getClass()) )
				println("XML Name......: " + XML_RESUME_CLASS.cast(bioAny).getName());
		}

		println("Marshal.......: " + customer);
		Marshaller marshaller = createMainMarshaller();
		marshaller.marshal(customer, System.out);
	}
	
	private static void print(String value)
	{
        System.out.print(value);
	}
	
	private static void println(String value)
	{
        System.out.println(value);
	}
}
