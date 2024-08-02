package org.jdesktop.beansbinding;

import java.util.List;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class Result
{
	private final Type type;
	private final Object result;
	private final List<ResolvedProperty> resolvedProperties;

	public enum Type
	{
		UNRESOLVABLE, VALUE
	}

	public Result(Type type, Object result, List<ResolvedProperty> resolvedProperties)
	{
		this.type = type;
		this.result = result;
		this.resolvedProperties = resolvedProperties;
		if ( type == null || resolvedProperties == null )
		{
			throw new NullPointerException("Type, result and resolvedProperties must be non-null");
		}
	}

	public Type getType()
	{
		return type;
	}

	public Object getResult()
	{
		return result;
	}

	public List<ResolvedProperty> getResolvedProperties()
	{
		// PENDING: Return a copy?
		return resolvedProperties;
	}
}
