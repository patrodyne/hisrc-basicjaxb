package org.jvnet.basicjaxb.test.fixed_value;

import static org.apache.commons.lang3.reflect.FieldUtils.readDeclaredStaticField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.StringWriter;

import org.example.document.Document1;
import org.example.document.FvChoice;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.w3c.dom.Node;

import jakarta.xml.bind.JAXBException;

@Order(2)
public class FixedValue1Test extends AbstractSamplesTest
{
	protected Document1 document1A = null;
	protected Document1 document1B = null;

	@Override
	protected String getContextPath()
	{
		return ObjectFactory.class.getPackageName();
	}
	
	@Override
	protected void checkSample(File sampleFile)
		throws Exception
	{
		assertNotNull(sampleFile, "Sample must exist.");
	}
	
	@BeforeEach
	public void beforeEach() throws JAXBException
	{
		// Document1 is mostly FIXED; thus, the unmarshal should not change any values except fvChoice.
		document1A = (Document1) getUnmarshaller().unmarshal(getSampleMap().get("document1.xml"));
		document1B = new Document1();
		document1B.setFvChoice(new FvChoice());
	}

	@Test
	public void testFixedValue1AB() throws JAXBException, IllegalAccessException
	{
		StringWriter sw1A = new StringWriter();
		getMarshaller().marshal(document1A, sw1A);
		String doc1A = sw1A.toString();
		getLogger().debug("doc1A: {}\n", doc1A);

		StringWriter sw1B = new StringWriter();
		getMarshaller().marshal(document1B, sw1B);
		String doc1B = sw1B.toString();
		getLogger().debug("doc1B: {}\n", doc1B);

		// document1A: All attributes and elements have FIXED values, see document1.xml.
		// document1B: All attributes and elements have FIXED values.
		assertEquals(doc1A, doc1B, "document1A is FIXED and document1B is FIXED");
		assertEquals(document1A, document1B, "document1A is FIXED and document1B is FIXED");
		
		getLogger().debug("document1A: {}", document1A);

		Object FV_ANY_SIMPLE_TYPE = readDeclaredStaticField(Document1.class, "FV_ANY_SIMPLE_TYPE");
		if ( FV_ANY_SIMPLE_TYPE instanceof Node)
			logAnySimpleType("A", (Node) FV_ANY_SIMPLE_TYPE);
	}
	
	private void logAnySimpleType(String label, Node dvAnySimpleTypeNode)
	{
		if ( getLogger().isDebugEnabled() )
		{
			getLogger().debug(label + " BaseURI: " + dvAnySimpleTypeNode.getBaseURI());
			getLogger().debug(label + " NamespaceURI: " + dvAnySimpleTypeNode.getNamespaceURI());
			getLogger().debug(label + " LocalName: " + dvAnySimpleTypeNode.getLocalName());
			getLogger().debug(label + " Prefix: " + dvAnySimpleTypeNode.getPrefix());
			getLogger().debug(label + " NodeName: " + dvAnySimpleTypeNode.getNodeName());
			getLogger().debug(label + " NodeType: " + dvAnySimpleTypeNode.getNodeType());
			getLogger().debug(label + " NodeValue: " + dvAnySimpleTypeNode.getNodeValue());
			if ( dvAnySimpleTypeNode.getAttributes() != null )
			{
				getLogger().debug(label + " Attributes: " + dvAnySimpleTypeNode.getAttributes().getLength());
				for ( int index = 0; index < dvAnySimpleTypeNode.getAttributes().getLength(); ++index)
					logAnySimpleType(label + "[" + index+"]", dvAnySimpleTypeNode.getAttributes().item(index));
			}
			getLogger().debug(label + " TextContent: " + dvAnySimpleTypeNode.getTextContent());
		}
	}
}
