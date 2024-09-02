package org.swixml.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.swixml.Parser;
import org.swixml.annotation.SchemaAware;
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
	
	@Override
	protected void addCustomAttributes(Element elem)
	{
		Set<String> attributes = new HashSet<String>();
		//
		// add custom swixml attributes
		//
		for ( Field field : Parser.class.getFields() )
		{
			if ( field.getName().startsWith("ATTR_") && !field.getName().endsWith("PREFIX")
				&& Modifier.isFinal(field.getModifiers()) )
			{
				try
				{
					SchemaAware schema = field.getAnnotation(SchemaAware.class);
					if ( schema != null )
					{
						Deprecated deprecated = field.getAnnotation(Deprecated.class);
						String aName = field.get(Parser.class).toString().toLowerCase();
						Element e = addAttribute(attributes, elem, aName, String.class);
						if ( e != null && deprecated != null )
							addDocumentation(e, "deprecated");
					}
				}
				catch (IllegalAccessException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Writes the schema into the given file. Defaults to standard out.
	 *
	 * @param args The <code>String[]</code> arguments; specifically, a file name.
	 */
	public static void main(String[] args)
		throws Exception
	{
		SchemaGeneratorFlat sg = new SchemaGeneratorFlat();
		sg.execute(args);
	}
}
