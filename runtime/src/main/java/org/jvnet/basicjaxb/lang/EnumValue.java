package org.jvnet.basicjaxb.lang;

/**
 * This interface provides generic access to the original <code>enum</code> value.
 *
 * @param <T> Generic for the enumeration value type.
 */
public interface EnumValue<T>
{
	public T enumValue();
}
