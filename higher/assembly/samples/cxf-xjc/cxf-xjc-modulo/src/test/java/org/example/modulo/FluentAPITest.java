package org.example.modulo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.jvnet.basicjaxb.lang.ValueUtils.toXMLGregorianCalendar;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.example.modulo.pgr.Root;

import jakarta.xml.bind.JAXBElement;

public class FluentAPITest extends AbstractRootTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		final Object object = getUnmarshaller().unmarshal(sample);
		if ( object instanceof JAXBElement )
		{
			@SuppressWarnings("unchecked")
			Root root1 = ((JAXBElement<Root>) object).getValue();
			JAXBElement<? extends Serializable> contentItem2 = null;
	        Collection<JAXBElement<? extends Serializable>> content2 = new ArrayList<>();
	        for ( JAXBElement<? extends Serializable> contentItem1 : root1.getContent() )
	        {
	        	Serializable value1 = contentItem1.getValue();
	        	if ( value1 instanceof org.example.modulo.ia.Modulo )
	        	{
	        		org.example.modulo.ia.Modulo modulo2IA = OFIA.createModulo()
   	        			.useDenominazioneAzienda("Company Name")
   	        			.useIndirizzo("Address")
	        			.usePecAzienda("https://www.inipec.gov.it/cerca-pec")
	        			.useDataProvvedimento(toXMLGregorianCalendar("2024-01-01"))
	        			.useTipoPrestazione("Performance Type")
	        			.useNumeroProtocollo("Protocol Number")
	        			.useNumeroProvvedimento("Provision Number")
	        			.useAnnoProvvedimento("Provision Year")
	        			.useDataPresentazione(toXMLGregorianCalendar("2024-04-01"))
	        		;
	        		contentItem2 = new JAXBElement<Serializable>(contentItem1.getName(), Serializable.class, modulo2IA);
	        	}
	        	else if ( value1 instanceof org.example.modulo.pa.Modulo )
	        	{
	        		org.example.modulo.pa.Modulo modulo2PA = OFPA.createModulo()
	        			.useDenominazioneAzienda("Company Name")
	        			.useIndirizzo("Address")
	        			.usePecAzienda("https://www.inipec.gov.it/cerca-pec")
	        			.useDataProvvedimento(toXMLGregorianCalendar("2024-01-01"))
	        			.useTipoPrestazione("Performance Type")
	        			.useNumeroProtocollo("Protocol Number")
	        			.useNumeroProvvedimento("Provision Number")
	        			.useAnnoProvvedimento("Provision Year")
	        			.useDataPresentazione(toXMLGregorianCalendar("2024-04-01"))
	        			.useCausale("Yes");
	        		contentItem2 = new JAXBElement<Serializable>(contentItem1.getName(), Serializable.class, modulo2PA);
	        	}
	        	else
	        	{
	        		JAXBElement<String> frontespizioPostel2 = OFPGR.createRootFrontespizioPostel("Passport Photograph");
	        		contentItem2 = frontespizioPostel2;
	        	}
		        content2.add(contentItem2);
	        }
			
			Root root2 = OFPGR.createRoot()
				.useContent(content2);
			
			getLogger().debug("Root1: {}", root1);
			getLogger().debug("Root2: {}", root2);
			
			assertEquals(root1, root2, "Unmarshaled and Fluent Roots are equal.");
		}
		else
			fail("Sample is not a Root: " + object);
	}
}
