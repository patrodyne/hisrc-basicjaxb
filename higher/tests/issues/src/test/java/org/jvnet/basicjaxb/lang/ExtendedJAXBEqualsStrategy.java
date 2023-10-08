package org.jvnet.basicjaxb.lang;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.dom.DOMSource;

import org.custommonkey.xmlunit.Diff;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.w3c.dom.Node;

/**
 * An extension of {@link JAXBEqualsStrategy} to override the {@link Object}
 * strategy and to provides additional strategies for {@link XMLGregorianCalendar}
 * and DOM {@link Node}.
 */
public class ExtendedJAXBEqualsStrategy extends JAXBEqualsStrategy
{
	/**
	 * Extend the {@link JAXBEqualsStrategy} {@link Object} method to dispatch strategies for
	 * {@link Node} and {@link XMLGregorianCalendar}.
	 */
	@Override
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs instanceof Node && rhs instanceof Node)
			return equalsInternal(lhsLocator, rhsLocator, (Node) lhs, (Node) rhs);
		else if (lhs instanceof XMLGregorianCalendar && rhs instanceof XMLGregorianCalendar)
			return equalsInternal(lhsLocator, rhsLocator, (XMLGregorianCalendar) lhs, (XMLGregorianCalendar) rhs);
		else
			return super.equalsInternal(lhsLocator, rhsLocator, lhs, rhs);
	}

	/**
	 * Provide a strategy to equate {@link XMLGregorianCalendar} pairs by <em>normalizing</em>
	 * each instance to UTC then equating by time in milliseconds.
	 * 
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side {@link XMLGregorianCalendar}.
	 * @param rhs The right hand side {@link XMLGregorianCalendar}.
	 * 
	 * @return True when the UTC time in milliseconds is the same for both instances; otherwise, false.
	 */
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator,
		XMLGregorianCalendar lhs, XMLGregorianCalendar rhs)
	{
		long lhsMilliseconds = lhs.normalize().toGregorianCalendar().getTimeInMillis();
		long rhsMilliseconds = rhs.normalize().toGregorianCalendar().getTimeInMillis();
		return equals(lhsLocator, rhsLocator, lhsMilliseconds, rhsMilliseconds);
	}

	/**
	 * Provide a strategy to equate DOM {@link Node} pairs using the {@link Diff} utility
	 * from XMLUnit to detect differences.
	 * 
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side {@link Node}.
	 * @param rhs The right hand side {@link Node}.
	 * 
	 * @return True when both nodes contain the same elements and attributes in the same order; otherwise, false.
	 */
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Node lhs, Node rhs)
	{
		final Diff diff = new Diff(new DOMSource(lhs), new DOMSource(rhs));
		return diff.identical();
	}
}
