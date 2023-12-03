package org.example.platce;

import static org.example.platce.Context.METHODXX1_CLASS;
import static org.example.platce.Context.METHODXX2_CLASS;
import static org.example.platce.Context.createMainMarshaller;
import static org.example.platce.Context.createMainUnmarshaller;
import static org.example.platce.util.DOMUtils.buildToString;
import static org.example.platce.util.XmlTypeUtils.isJAXBElement;

import java.io.File;

import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * @see <a href="https://stackoverflow.com/questions/77479159/">Unmarshalling Multiple XMLs with Similar Wrapper</a>
 */
public class Demo
{
	private static final String TAB = "  ";
	
	private static String sampleFileName = "src/test/samples/response01.xml";
	public static String getSampleFileName() { return sampleFileName; }
	public static void setSampleFileName(String sampleFileName) { Demo.sampleFileName = sampleFileName; }

	public static void main(String[] args) throws Exception
	{
		if ( args.length == 1 )
			setSampleFileName(args[0]);

		Unmarshaller unmarshaller = createMainUnmarshaller();
		println("Unmarshal......: " + sampleFileName);
		Response response = (Response) unmarshaller.unmarshal(new File(getSampleFileName()));
		
		VeraWSClass vera = response.getClazz();
		Object veraAny = vera.getAny();
		
		println("Id.............: " + response.getId());
		println("Vera Name......: " + vera.getName());
		println("Vera Any.......: " + veraAny);
		println("Vera Any Class.: " + veraAny.getClass());
		if ( isJAXBElement(veraAny) ) 
			println("Vera Any Value.: " + ((JAXBElement<?>) veraAny).getValue() );

		if ( veraAny != null )
		{
			if ( veraAny instanceof Element )
			{
				Element veraElement = (Element) veraAny;
				
				println("----------------------------------------");
				print(buildToString(TAB, veraElement.getOwnerDocument()));
				println("----------------------------------------");
				print(buildToString(TAB, veraElement));
				println("----------------------------------------");
			}
			else if ( METHODXX1_CLASS.equals(veraAny.getClass()) )
				println("XX1 Name.......: " + METHODXX1_CLASS.cast(veraAny).getName());
			else if ( METHODXX2_CLASS.equals(veraAny.getClass()) )
				println("XX2 Name.......: " + METHODXX2_CLASS.cast(veraAny).getName());
		}

		println("Marshal........: " + response);
		Marshaller marshaller = createMainMarshaller();
		marshaller.marshal(response, System.out);
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
