package org.jvnet.basicjaxb.lang;

import static org.jvnet.basicjaxb.lang.StringUtils.valueToString;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.item;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.property;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.PropertyObjectLocator;
import org.jvnet.basicjaxb.locator.RootObjectLocator;
import org.w3c.dom.Node;

import jakarta.xml.bind.JAXBElement;

public class JAXBCopyStrategy extends DefaultCopyStrategy
{
	private static final JAXBCopyStrategy INSTANCE = new JAXBCopyStrategy();

	public static JAXBCopyStrategy getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Observe an copy object and its locator.
	 * 
	 * In TRACE mode, log the the entire object tree.
	 * 
	 * In DEBUG mode, log the top level object, handle JAXBElement wrapped root.
	 * 
	 * @param <T> The observed object's type.
	 * @param label A prefix for the observation message.
	 * @param locator The object locator.
	 * @param obj The object to observe.
	 * 
	 * @return The original observed object.
	 */
	@Override
	protected <T> T observe(String label, ObjectLocator locator, T obj)
	{
		if ( isTraceEnabled() )
			trace(buildMessage(label, locator, valueToString(obj)));
		else if ( isDebugEnabled() )
		{
			if ( locator != null )
			{
				// Log the top level object, handle JAXBElement wrapped root.
				if ( locator instanceof RootObjectLocator )
				{
					// Root object value.
					debug(buildMessage(label, locator, valueToString(obj)));
				}
				else if ( locator.getParentLocator() instanceof RootObjectLocator )
				{
					RootObjectLocator rootObjectLocator = (RootObjectLocator) locator.getParentLocator();
					if ( rootObjectLocator.getObject() instanceof JAXBElement<?> )
					{
						// Root wraps a JAXBElement, log object value for 'value' property only.
						if ( locator instanceof PropertyObjectLocator )
						{
							PropertyObjectLocator propertyLocator = (PropertyObjectLocator) locator;
							if ( "value".equals(propertyLocator.getPropertyName())  )
								debug(buildMessage(label, locator, valueToString(obj)));
						}
					}
					else
					{
						// Root does not wrap a JAXBElement, log object value for each top level property.
						debug(buildMessage(label, locator, valueToString(obj)));
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
									debug(buildMessage(label, locator, valueToString(obj)));
							}
						}
					}
				}
			}
		}			
		return obj;
	}
	
	@Override
	protected Object copyInternal(ObjectLocator locator, Object object)
	{
		if (object instanceof Node)
		{
			final Node node = (Node) object;
			return copyInternal(locator, node);
		}
		else if (object instanceof JAXBElement)
		{
			@SuppressWarnings("rawtypes")
			final JAXBElement jaxbElement = (JAXBElement) object;
			return copyInternal(locator, jaxbElement);
		}
		else if (object instanceof List)
		{
			@SuppressWarnings("rawtypes")
			List list = (List) object;
			return copyInternal(locator, list);
		}
		else
			return super.copyInternal(locator, object);
	}

	protected Object copyInternal(ObjectLocator locator, final Node node)
	{
		return observe(locator, node.cloneNode(true));
	}

	@SuppressWarnings("unchecked")
	protected Object copyInternal(ObjectLocator locator, @SuppressWarnings("rawtypes") final JAXBElement jaxbElement)
	{
		final Object sourceObject = jaxbElement.getValue();
		final Object copyObject = copy(property(locator, "value", sourceObject), sourceObject);
		@SuppressWarnings("rawtypes")
		final JAXBElement copyElement =
			new JAXBElement(jaxbElement.getName(), jaxbElement.getDeclaredType(), jaxbElement.getScope(), copyObject);
		return observe(locator, copyElement);
	}

	@SuppressWarnings("unchecked")
	protected Object copyInternal(ObjectLocator locator, @SuppressWarnings("rawtypes") List list)
	{
		@SuppressWarnings("rawtypes")
		final List copy = new ArrayList(list.size());
		for (int index = 0; index < list.size(); index++)
		{
			final Object element = list.get(index);
			final Object copyElement = copy(item(locator, index, element), element);
			copy.add(copyElement);
		}
		return observe(locator, copy);
	}
}