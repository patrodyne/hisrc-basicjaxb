package org.jvnet.basicjaxb.lang;

import static java.lang.Integer.toHexString;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.item;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.property;

import java.util.List;

import jakarta.xml.bind.JAXBElement;

import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.PropertyObjectLocator;
import org.jvnet.basicjaxb.locator.RootObjectLocator;

public class JAXBHashCodeStrategy extends DefaultHashCodeStrategy
{
	private static JAXBHashCodeStrategy INSTANCE = new JAXBHashCodeStrategy();

	public static JAXBHashCodeStrategy getInstance()
	{
		return INSTANCE;
	}

	public JAXBHashCodeStrategy()
	{
		super();
	}

	public JAXBHashCodeStrategy(int multiplierNonZeroOddNumber)
	{
		super(multiplierNonZeroOddNumber);
	}

	/**
	 * Observe an int hash and its locator.
	 * 
	 * In TRACE mode, log the hash codes for the entire tree.
	 * 
	 * In DEBUG mode, log the top level object, handle JAXBElement wrapped root.
	 * 
	 * @param label A prefix for the observation message.
	 * @param locator The object locator.
	 * @param hash The hash integer.
	 * 
	 * @return The original hash.
	 */
	@Override
	protected int observe(String label, ObjectLocator locator, int hash)
	{
		if ( isTraceEnabled() )
			trace(buildMessage(label, locator, toHexString(hash)));
		else if ( isDebugEnabled() )
		{
			if ( locator != null )
			{
				// Log the top level object, handle JAXBElement wrapped root.
				if ( locator instanceof RootObjectLocator )
				{
					// Root hash code.
					debug(buildMessage(label, locator, toHexString(hash)));
				}
				else if ( locator.getParentLocator() instanceof RootObjectLocator )
				{
					RootObjectLocator rootObjectLocator = (RootObjectLocator) locator.getParentLocator();
					if ( rootObjectLocator.getObject() instanceof JAXBElement<?> )
					{
						// Root wraps a JAXBElement, log hash code for 'value' property only.
						if ( locator instanceof PropertyObjectLocator )
						{
							PropertyObjectLocator propertyLocator = (PropertyObjectLocator) locator;
							if ( "value".equals(propertyLocator.getPropertyName())  )
								debug(buildMessage(label, locator, toHexString(hash)));
						}
					}
					else
					{
						// Root does not wrap a JAXBElement, log hash code for each top level property.
						debug(buildMessage(label, locator, toHexString(hash)));
					}
				}
				else
				{
					// If the parent locator is a PropertyObjectLocator and this locator is
					// a 'value' property and if the grandparent of this locator is the
					// RootObjectLocator and the root object is a JAXBElement then log 
					// this locator's properties.
					if ( locator.getParentLocator() instanceof PropertyObjectLocator )
					{
						PropertyObjectLocator parentPropertyLocator = (PropertyObjectLocator) locator.getParentLocator();
						if ( "value".equals(parentPropertyLocator.getPropertyName()) )
						{
							if ( locator.getParentLocator().getParentLocator() instanceof RootObjectLocator )
							{
								RootObjectLocator rootObjectLocator = (RootObjectLocator) locator.getParentLocator().getParentLocator();
								if ( rootObjectLocator.getObject() instanceof JAXBElement )
									debug(buildMessage(label, locator, toHexString(hash)));
							}
						}
					}
				}
			}
		}			
		return hash;
	}
	
	@Override
	protected int hashCodeInternal(ObjectLocator locator, int hashCode, Object value)
	{
		if (value instanceof JAXBElement<?>)
		{
			final JAXBElement<?> element = (JAXBElement<?>) value;
			return hashCodeInternal(locator, hashCode, element);
		}
		else if (value instanceof List<?>)
		{
			final List<?> list = (List<?>) value;
			return hashCodeInternal(locator, hashCode, list);
		}
		else
			return super.hashCodeInternal(locator, hashCode, value);
	}

	protected int hashCodeInternal(ObjectLocator locator, int hashCode, final JAXBElement<?> element)
	{
		int currentHashCode = hashCode;
		currentHashCode = hashCode(property(locator, "name", element.getName()), currentHashCode, element.getName());
		currentHashCode = hashCode(property(locator, "declaredType", element.getDeclaredType()), currentHashCode, element.getDeclaredType());
		currentHashCode = hashCode(property(locator, "scope", element.getScope()), currentHashCode, element.getScope());
		currentHashCode = hashCode(property(locator, "value", element.getValue()), currentHashCode, element.getValue());
		return observe(locator, currentHashCode);
	}

	protected int hashCodeInternal(ObjectLocator locator, int hashCode, final List<?> list)
	{
		// Treat empty lists as nulls
		if (list.isEmpty())
			return super.hashCode(locator, hashCode, (Object) null);
		else
		{
			int currentHashCode = hashCode;
			for (int index = 0; index < list.size(); index++)
			{
				final Object item = list.get(index);
				currentHashCode = hashCode(item(locator, index, item), currentHashCode, item);
			}
			return observe(locator, currentHashCode);
		}
	}
}