package org.jvnet.basicjaxb.lang;

import static org.jvnet.basicjaxb.locator.util.LocatorUtils.item;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.property;

import java.util.Iterator;
import java.util.List;

import jakarta.xml.bind.JAXBElement;

import org.jvnet.basicjaxb.locator.ObjectLocator;

/**
 * Extend the {@link DefaultEqualsStrategy} by overriding the {@link Object} strategy
 * and providing strategies for {@link List} and {@link JAXBElement}.
 */
public class JAXBEqualsStrategy extends DefaultEqualsStrategy
{
	// Represents a single instance of this class.
	private static JAXBEqualsStrategy INSTANCE = new JAXBEqualsStrategy();
	/**
	 * Get the single instance of {@link JAXBEqualsStrategy}.
	 * 
	 * @return The single instance of {@link JAXBEqualsStrategy}.
	 */
	public static JAXBEqualsStrategy getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 * Extend the default {@link Object} method to dispatch strategies for
	 * {@link List} and {@link JAXBElement}.
	 */
	@Override
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs instanceof JAXBElement<?> && rhs instanceof JAXBElement<?>)
			return equalsInternal(lhsLocator, rhsLocator, (JAXBElement<?>) lhs, (JAXBElement<?>) rhs);
		else if (lhs instanceof List<?> && rhs instanceof List<?>)
			return equalsInternal(lhsLocator, rhsLocator, (List<?>) lhs, (List<?>) rhs);
		else
			return super.equalsInternal(lhsLocator, rhsLocator, lhs, rhs);
	}

	/**
	 * Provide an <em>equals</em> strategy for {@link List} to iterate over the element pairs in the
	 * two lists and test each pair for {@link Object} equality.
	 * 
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side list.
	 * @param rhs The right hand side list.
	 * 
	 * @return True when all element pairs are equal and both lists are exhausted; otherwise, false.
	 */
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, final List<?> lhs, final List<?> rhs)
	{
		final Iterator<?> e1 = lhs.iterator();
		final Iterator<?> e2 = rhs.iterator();
		int index = 0;
		
		while (e1.hasNext() && e2.hasNext())
		{
			Object o1 = e1.next();
			Object o2 = e2.next();
			if ( !(o1 == null ? o2 == null : equals(item(lhsLocator, index, o1), item(rhsLocator, index, o2), o1, o2)) )
				return false;
			index = index + 1;
		}
		
		return !(e1.hasNext() || e2.hasNext());
	}

	/**
	 * Provide an <em>equals</em> strategy for {@link JAXBElement} to equate by XML element tag and
	 * by by value {@link Object}.
	 * 
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side {@link JAXBElement}.
	 * @param rhs The right hand side {@link JAXBElement}.
	 * 
	 * @return True when the element tags and values are equal; otherwise, false.
	 */
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, final JAXBElement<?> lhs, final JAXBElement<?> rhs)
	{
		return
			equals(property(lhsLocator, "name", lhs.getName()), property(rhsLocator, "name", rhs.getName()), lhs.getName(), rhs.getName()) &&
			equals(property(lhsLocator, "value", lhs.getValue()), property(rhsLocator, "value", rhs.getValue()), lhs.getValue(), rhs.getValue());
	}
}