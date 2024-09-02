package org.swixml.schema;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.table.TableColumn;

import org.swixml.Factory;
import org.swixml.Parser;
import org.swixml.TagLibrary;
import org.swixml.XGlue;
import org.swixml.XSplitPane;
import org.swixml.annotation.SchemaAware;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Normalized Swixml XML Schema Generator
 *
 * @author Wolf Paulus
 */
public class SchemaGeneratorNorm extends SchemaGeneratorBase
{
	@Override
	protected void appendElements(Document doc, Element root, TagLibrary taglib, TreeSet<String> treeSet)
	{
		for ( String name : treeSet )
		{
			Factory factory = taglib.getTagClasses().get(name);
			String type = "tns:" + getComplexTypeName(factory.getTemplate());
			switch ( name )
			{
				case "buttongroup":
					addComplexTypeHierarchy(doc, ButtonGroup.class);
					root.appendChild(createElement(doc, name, type));
					break;
				case "dialog":
					root.appendChild(createRootElement(doc, "dialog", "tns:XDialog"));
					break;
				case "frame":
					root.appendChild(createRootElement(doc, "frame", "tns:JFrame"));
					break;
				case "glue":
					addComplexTypeHierarchy(doc, XGlue.class);
					root.appendChild(createElement(doc, name, type));
					break;
				case "radiobuttonmenuitem":
					addComplexTypeHierarchy(doc, JRadioButtonMenuItem.class);
					root.appendChild(createElement(doc, name, type));
					break;
				case "splitpane":
					addComplexTypeHierarchy(doc, XSplitPane.class);
					root.appendChild(createElement(doc, name, type));
					break;
				default:
					root.appendChild(createElement(doc, name, type));
					break;
			}
		}
	}

	private String getComplexTypeName(Class<?> template)
	{
		String templateName = null;
		if ( template.equals(Dialog.class) )
			templateName = "WDialog";
		else if ( template.equals(Frame.class) )
			templateName = "WFrame";
		else
		{
			templateName = template.getName();
			int dotIndex = templateName.lastIndexOf('.');
			templateName = templateName.substring(dotIndex+1);
			templateName = templateName.replaceAll("\\$", "");
		}
		return templateName;
	}

	private Element createRootElement(Document doc, String name, String type)
	{
		Element element = doc.createElement("xs:element");
		{
			element.setAttribute("name", name);
			{
				Element elementType = doc.createElement("xs:complexType");
				{
					//elementType.setAttribute("mixed", "true");
					Element elementContent = doc.createElement("xs:complexContent");
					elementType.appendChild(elementContent);
					{
						Element elementExtension = doc.createElement("xs:extension");
						elementExtension.setAttribute("base", type);
						elementContent.appendChild(elementExtension);
					}
				}
				element.appendChild(elementType);
			}
		}
		return element;
	}

	private Element createElement(Document doc, String name, String type)
	{
		Element element = null;
		if ( type != null )
		{
			element = doc.createElement("xs:element");
			element.setAttribute("name", name);
			element.setAttribute("type", type);
		}
		else
		{
			element = doc.createElement("xs:element");
			{
				element.setAttribute("name", name);
				{
					Element elementType = doc.createElement("xs:complexType");
					{
						elementType.setAttribute("mixed", "true");
						Element sequence = doc.createElement("xs:sequence");
						elementType.appendChild(sequence);
						{
							Element any = doc.createElement("xs:any");
							sequence.appendChild(any);
							sequence.setAttribute("minOccurs", "0");
							sequence.setAttribute("maxOccurs", "unbounded");
						}
					}
					element.appendChild(elementType);
				}
			}
		}
		return element;
	}
	
	@Override
	protected Element addAttribute(Set<String> attributes, Element elem, String aName,
		Method setter)
	{
		Document doc = elem.getOwnerDocument();
		Class<?> type = setter.getParameterTypes()[0];
		Class<?> dc = setter.getDeclaringClass();
		
		Element attribute = null;
		if ( !attributes.contains(aName) )
		{
			addComplexTypeHierarchy(doc, dc);
			
			// Find highest element in the hierarchy having the attribute method.
			Class<?> bc = dc, sc = dc.getSuperclass();
			while ( (sc != null) && !Object.class.equals(sc) )
			{
				try
				{
					if ( ! ( "cursor".equals(aName) || "enabled".equals(aName) ) ) 
						FindMethod.findMethod(sc, setter.getName(), setter.getParameterTypes());
					bc = sc;
					sc = sc.getSuperclass();
				}
				catch (SecurityException | NoSuchMethodException nsme)
				{
					break;
				}
			}
			Element baseElement = getComplexTypeByName(doc, getComplexTypeName(bc));
			
			if ( baseElement.getFirstChild() != null )
			{
				Node node = baseElement.getFirstChild().getFirstChild();
				if ( node != null && "xs:extension".equals(node.getNodeName()) )
					baseElement = (Element) node;
			}
			
			if ( getAttributeByName(baseElement, aName) == null  )
				attribute = addAttribute(attributes, baseElement, aName, type);
		}
		return attribute;
	}
	
	@Override
	protected void addCustomAttributes(Element elem)
	{
		if ( elem != null )
		{
			Document doc = elem.getOwnerDocument();
			//
			// add custom swixml attributes to Component, etc.
			//
			addCustomAttributes(doc, Component.class);
			addCustomAttributes(doc, ButtonGroup.class);
			addCustomAttributes(doc, GridBagConstraints.class);
			addCustomAttributes(doc, TableColumn.class);
		}
	}

	private void addCustomAttributes(Document doc, Class<?> clazz)
	{
		Set<String> attributes = new HashSet<String>();
		Set<String> aNameSet = new TreeSet<>();
		Element target = getComplexTypeByName(doc, clazz.getSimpleName());
		if ( target == null )
		{
			addComplexTypeHierarchy(doc, clazz);
			target = getComplexTypeByName(doc, clazz.getSimpleName());
		}
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
						if ( deprecated == null )
						{
							String aName = field.get(Parser.class).toString().toLowerCase();
							if ( getAttributeByName(target, aName) == null  )
								aNameSet.add(aName);
						}
					}
				}
				catch (IllegalAccessException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		for ( String aName : aNameSet )
			addAttribute(attributes, target, aName, String.class);
	}

	private void addComplexTypeHierarchy(Document doc, Class<?> dc)
	{
		String complexTypeName = getComplexTypeName(dc);
		Element complexType = getComplexTypeByName(doc, complexTypeName);
		if ( complexType == null )
		{
			complexType = doc.createElement("xs:complexType");
			complexType.setAttribute("name", complexTypeName);
			//complexType.setAttribute("mixed", "true");

			Node root = doc.getFirstChild();
			root.appendChild(complexType);
			Class<?> sc = dc.getSuperclass();
			if ( (sc != null) && !Object.class.equals(sc) )
			{
				String superComplexTypeName = getComplexTypeName(sc);
				Element complexContent = doc.createElement("xs:complexContent");
				Element extension = doc.createElement("xs:extension");
				extension.setAttribute("base", "tns:" + superComplexTypeName);
				Element sequence = doc.createElement("xs:sequence");
				extension.appendChild(sequence);
				complexContent.appendChild(extension);
				complexType.appendChild(complexContent);
				addComplexTypeHierarchy(doc, sc);
			}
			else
			{
				complexType.setAttribute("mixed", "true");
				Element sequence = doc.createElement("xs:sequence");
				Element any = doc.createElement("xs:any");
				sequence.appendChild(any);
				sequence.setAttribute("minOccurs", "0");
				sequence.setAttribute("maxOccurs", "unbounded");
				complexType.appendChild(sequence);
			}
		}
	}
	
	private Element getAttributeByName(Node node, String attributeName)
	{
		Element attribute = null;
		NodeList childList = node.getChildNodes();
		for ( int index = 0; index < childList.getLength(); ++index )
		{
			Element child = (Element) childList.item(index);
			if ( "xs:attribute".equals(child.getNodeName()) )
			{
				if ( attributeName.equals(child.getAttribute("name")) )
				{
					attribute = child;
					break;
				}
			}
		}
		return attribute;
	}

	private Element getComplexTypeByName(Document doc, String complexTypeName)
	{
		Element complexType = null;
		NodeList nodeList = doc.getElementsByTagName("xs:complexType");
		int nodeListSize = nodeList.getLength();
		for ( int index=0; index < nodeListSize; ++index )
		{
			Element elem = (Element) nodeList.item(index);
			if ( complexTypeName.equals(elem.getAttribute("name")) )
			{
				complexType = elem;
				break;
			}
		}
		return complexType;
	}
	
	/**
	 * Writes the schema into the given file. Defaults to standard out.
	 *
	 * @param args The <code>String[]</code> arguments; specifically, a file name.
	 */
	public static void main(String[] args)
		throws Exception
	{
		SchemaGeneratorNorm sg = new SchemaGeneratorNorm();
		sg.execute(args);
	}
}
