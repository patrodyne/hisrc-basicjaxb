package org.swixml.schema;

import java.lang.reflect.Method;
import java.util.Set;

import org.w3c.dom.Element;

/**
 * Flat Swixml XML Schema Generator
 *
 * @author Wolf Paulus
 */
public class SchemaGeneratorFlat extends SchemaGeneratorBase
{
	@Override
	protected void appendElements(Element root, Element tag)
	{
		root.appendChild(tag);
	}
	
	@Override
	protected Element addAttribute(Set<String> attributes, Element elem, String aName,
		Method setter)
	{
		Class<?> type = setter.getParameterTypes()[0];
		return addAttribute(attributes, elem, aName, type);
	}
	
	/**
	 * Writes the schema into the given file. Defaults to standard out.
	 *
	 * @param args The <code>String[]<code> arguments; specifically, a file name.
	 */
	public static void main(String[] args)
		throws Exception
	{
		SchemaGeneratorFlat sg = new SchemaGeneratorFlat();
		sg.execute(args);
	}
}
