package org.jvnet.basicjaxb.lang;

import static org.jvnet.basicjaxb.lang.StringUtils.valueToString;

import java.util.Collection;

import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.PropertyObjectLocator;
import org.jvnet.basicjaxb.locator.RootObjectLocator;

import jakarta.xml.bind.JAXBElement;

public class JAXBMergeStrategy extends DefaultMergeStrategy
{
	private static final JAXBMergeStrategy INSTANCE = new JAXBMergeStrategy();

	public static JAXBMergeStrategy getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Observe an merge object and its locator.
	 * 
	 * In TRACE mode, log the the entire object tree.
	 * 
	 * In DEBUG mode, log the top level object, handle JAXBElement wrapped root.
	 * 
	 * @param <T> The observed object's type.
	 * @param side Label for "LHS" or "RHS".
	 * @param locator The object locator.
	 * @param obj The object to observe.
	 * 
	 * @return The original observed object.
	 */
	@Override
	protected <T> T observe(String side, ObjectLocator locator, T obj)
	{
		if ( isTraceEnabled() )
			trace(buildMessage("MERGE (" + side + ")", locator, valueToString(obj)));
		else if ( isDebugEnabled() )
		{
			if ( locator != null )
			{
				// Log the top level object, handle JAXBElement wrapped root.
				if ( locator instanceof RootObjectLocator )
				{
					// Root object value.
					debug(buildMessage("MERGE (" + side + ")", locator, valueToString(obj)));
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
								debug(buildMessage("MERGE (" + side + ")", locator, valueToString(obj)));
						}
					}
					else
					{
						// Root does not wrap a JAXBElement, log object value for each top level property.
						debug(buildMessage("MERGE (" + side + ")", locator, valueToString(obj)));
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
									debug(buildMessage("MERGE (" + side + ")", locator, valueToString(obj)));
							}
						}
					}
				}
			}
		}			
		return obj;
	}
	
	@Override
	protected Object mergeInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs instanceof Collection && rhs instanceof Collection)
		{
			@SuppressWarnings("rawtypes")
			Collection lhsCollection = (Collection) lhs;
			@SuppressWarnings("rawtypes")
			Collection rhsCollection = (Collection) rhs;
			return mergeInternal(lhsLocator, rhsLocator, lhsCollection, rhsCollection);
		}
		else
			return super.mergeInternal(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected Object mergeInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator,
		@SuppressWarnings("rawtypes") Collection lhsCollection,
		@SuppressWarnings("rawtypes") Collection rhsCollection)
	{
		return !lhsCollection.isEmpty() ? observe("LHS", lhsLocator, lhsCollection) : observe("RHS", rhsLocator, rhsCollection);
	}
}