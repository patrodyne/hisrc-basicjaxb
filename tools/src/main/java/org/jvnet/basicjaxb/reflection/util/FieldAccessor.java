package org.jvnet.basicjaxb.reflection.util;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * FieldAccessor provides methods to get and set fields by reflection.
 * 
 * A {@code Field} provides information about, and dynamic access to, a
 * single field of a class or an interface.  The reflected field may
 * be a class (static) field or an instance field.
 * 
 * @param <T> The declared type for the field represented by this Field object.
 */
public class FieldAccessor<T> implements Accessor<T>
{
	/* Represents a single field of a class or an interface. */
	private final Field field;

	/**
	 * Construct with the owning class, field name and field type.
	 * 
	 * @param owner The owning class.
	 * @param fieldName The field name.
	 * @param fieldType The field type.
	 */
	public FieldAccessor(Class<?> owner, String fieldName, Class<T> fieldType)
	{
		try
		{
			this.field = owner.getDeclaredField(fieldName);
		}
		catch (NoSuchFieldException nsfex)
		{
			throw new IllegalArgumentException(
				MessageFormat.format("Could not retrieve the field [{0}] from the class [{1}].", fieldName, owner),	nsfex);
		}
		catch (SecurityException sex)
		{
			throw new IllegalArgumentException(
				MessageFormat.format("Could not retrieve the field [{0}] from the class [{1}].", fieldName, owner), sex);
		}
		
		if (!fieldType.equals(this.field.getType()))
		{
			throw new IllegalArgumentException(
				MessageFormat.format("The field [{0}] does not have the expected type [{1}].", this.field, fieldType));
		}
		
		try
		{
			field.setAccessible(true);
		}
		catch (SecurityException sex)
		{
			throw new IllegalArgumentException(MessageFormat
				.format("Could not make the field [{0}] of the class [{1}] accessible.", this.field, owner), sex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Object target)
	{
		requireNonNull(target);
		try
		{
			return (T) field.get(target);
		}
		catch (IllegalAccessException iaex)
		{
			throw new IllegalArgumentException(iaex);
		}
	}

	@Override
	public void set(Object target, T value)
	{
		Objects.requireNonNull(target);
		try
		{
			this.field.set(target, value);
		}
		catch (IllegalAccessException iaex)
		{
			throw new IllegalArgumentException(iaex);
		}
	}
}
