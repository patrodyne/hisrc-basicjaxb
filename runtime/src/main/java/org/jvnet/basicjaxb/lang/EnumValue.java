package org.jvnet.basicjaxb.lang;

/**
 * This interface provides general access to the original <code>enum</code> value.
 *
 * @param <T> The enumeration value type.
 */
public interface EnumValue<T>
{
	public T enumValue();
}
