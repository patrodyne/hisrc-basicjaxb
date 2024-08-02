package org.jdesktop.beansbinding;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class ResolvedProperty
{
	private final Object source;
	private final Object property;

	public ResolvedProperty(Object source, Object property)
	{
		this.source = source;
		this.property = property;
		if ( source == null || property == null )
		{
			throw new IllegalArgumentException("Source and property must be non-null");
		}
	}

	public Object getSource()
	{
		return source;
	}

	public Object getProperty()
	{
		return property;
	}

	@Override
	public boolean equals(Object o)
	{
		if ( o == this )
		{
			return true;
		}
		if ( o instanceof ResolvedProperty )
		{
			ResolvedProperty orp = (ResolvedProperty) o;
			return (orp.source == source && ((orp.property == null && property == null)
				|| (orp.property != null && orp.property.equals(property))));
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 17;
		hash = 37 * hash + source.hashCode();
		hash = 37 * hash + property.hashCode();
		return hash;
	}
}
