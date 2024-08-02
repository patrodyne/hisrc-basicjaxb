package org.swixml.dom;

import org.swixml.LogAware;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * A SWIXML attribute wraps a {@link org.w3c.dom.Attr}.
 * 
 * @author bsorrentino
 * 
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 */
public class Attribute implements LogAware
{
	public static final short CDATA_TYPE = 1;
	private final org.w3c.dom.Attr attr;
	private final String name;
	private String value;

	public Attribute(org.w3c.dom.Node attr)
	{
		if ( attr == null )
			throw new IllegalArgumentException("attr is null!");
		if ( !(attr instanceof org.w3c.dom.Attr) )
			throw new IllegalArgumentException("attr is not an Attribute node!");
		this.attr = (org.w3c.dom.Attr) attr;
		this.name = null;
		this.value = null;
	}

	public Attribute(String name, String value)
	{
		if ( name == null )
			throw new IllegalArgumentException("name is null!");
		this.attr = null;
		this.name = name;
		this.value = value;
	}

	public Attribute(String name, String value, short type)
	{
		if ( name == null )
			throw new IllegalArgumentException("name is null!");
		this.attr = null;
		this.name = name;
		this.value = value;
	}

	@Override
	public final Attribute clone()
	{
		return (null != attr) ? new Attribute(attr.cloneNode(true)) : new Attribute(name, new String(value));
	}

	public final String getValue()
	{
		return (attr != null) ? attr.getValue() : value;
	}

	public final void setValue(String v)
	{
		if ( attr != null )
			attr.setValue(v);
		else
			value = v;
	}

	public final String getLocalName()
	{
		if ( attr != null )
		{
			return (attr.getLocalName() == null) ? attr.getName() : attr.getLocalName();
		}
		return name;
	}

	public final String getNamespaceURI()
	{
		return (attr != null) ? attr.getNamespaceURI() : null;
	}

	public final String getPrefix()
	{
		return (attr != null) ? attr.getPrefix() : null;
	}

	public final int getIntValue(int def)
	{
		try
		{
			return getIntValue();
		}
		catch (NumberFormatException e)
		{
			logger.warn(String.format("error getIntValue[%s] for attribute [%s]. Default returned", getLocalName(),
				getValue()));
			return def;
		}
	}

	public final int getIntValue()
	{
		if ( getValue() == null )
			throw new NumberFormatException("value is null");
		return Integer.parseInt(getValue().trim());
	}

	public final boolean getBooleanValue()
	{
		if ( getValue() == null )
			throw new NumberFormatException("value is null");
		return Boolean.parseBoolean(getValue().trim());
	}

	public final long getLongValue()
	{
		if ( getValue() == null )
			throw new NumberFormatException("value is null");
		return Long.parseLong(getValue().trim());
	}

	public final float getFloatValue()
	{
		if ( getValue() == null )
			throw new NumberFormatException("value is null");
		return Float.parseFloat(getValue().trim());
	}

	public final double getDoubleValue()
	{
		if ( getValue() == null )
			throw new NumberFormatException("value is null");
		return Double.parseDouble(getValue().trim());
	}

	public static final java.util.List<Attribute> asList(java.util.List<Attribute> result, NamedNodeMap attributes)
	{
		if ( result == null )
			throw new IllegalArgumentException("result parameter is null!");
		if ( attributes == null || attributes.getLength() == 0 )
		{
			return result;
		}
		for ( int i = 0; i < attributes.getLength(); ++i )
		{
			Node n = attributes.item(i);
			// logger.info( "\tnode [%s] [%s] [%s] [%s]\n",
			// n.getLocalName(), n.getNodeValue(), n.getNamespaceURI(),
			// n.getPrefix());
			result.add(new Attribute(n));
		}
		return result;
	}
}
