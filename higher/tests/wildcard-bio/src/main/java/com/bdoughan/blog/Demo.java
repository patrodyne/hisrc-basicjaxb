package com.bdoughan.blog;

import static com.bdoughan.blog.BioXmlAdapter.HTML_RESUME_CLASS;
import static com.bdoughan.blog.BioXmlAdapter.XML_RESUME_CLASS;
import static org.jvnet.basicjaxb.dom.DOMUtils.buildToString;

import java.io.File;

import org.w3c.dom.Element;

import com.bdoughan.blog.domhandler.Bio;
import com.bdoughan.blog.domhandler.Customer;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * @see <a href="http://blog.bdoughan.com/2011/04/xmlanyelement-and-non-dom-properties.html">@XmlAnyElement and non-DOM Properties </a>
 */
public class Demo
{
	private static final String TAB = "  ";
	
	private static String sampleFileName = "src/test/samples/customer2.xml";
	public static String getSampleFileName() { return sampleFileName; }
	public static void setSampleFileName(String sampleFileName) { Demo.sampleFileName = sampleFileName; }

	public static void main(String[] args) throws Exception
	{
		if ( args.length == 1 )
			setSampleFileName(args[0]);
		
		JAXBContext jc = JAXBContext.newInstance(Customer.class);

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		println("Unmarshal.....: " + sampleFileName);
		Customer customer = (Customer) unmarshaller.unmarshal(new File(getSampleFileName()));
		
		Bio bio = customer.getBio();
		Object bioAny = bio.getAny();
		
		println("Name..........: " + customer.getName());
		println("Bio Type......: " + bio.getType());
		println("Bio Any.......: " + bioAny);
		println("Bio Any Class.: " + bioAny.getClass());

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
			{
				com.bdoughan.blog.domhandler.resume.html.Résumé résumé =
					(com.bdoughan.blog.domhandler.resume.html.Résumé) bioAny;
				println("HTML Name.....: " + résumé.getHtml().getBody().getH1());
			}
			else if ( XML_RESUME_CLASS.equals(bioAny.getClass()) )
			{
				com.bdoughan.blog.domhandler.resume.xml.Résumé résumé =
					(com.bdoughan.blog.domhandler.resume.xml.Résumé) bioAny;
				println("XML Name......: " + résumé.getName());
			}
		}

		println("Marshal.......: " + customer);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
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
