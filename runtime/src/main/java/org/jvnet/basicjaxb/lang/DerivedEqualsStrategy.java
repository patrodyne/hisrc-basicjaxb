package org.jvnet.basicjaxb.lang;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Objects;

import javax.xml.datatype.XMLGregorianCalendar;

import org.jvnet.basicjaxb.locator.ObjectLocator;

/**
 * A derivation of {@link DefaultEqualsStrategy} to override the {@link Object}
 * strategy and to provides additional strategies for {@link OffsetTime},
 * {@link OffsetDateTime}, {@link XMLGregorianCalendar}, and {@link Comparable} objects.
 */
public class DerivedEqualsStrategy extends DefaultEqualsStrategy
{
	private static DerivedEqualsStrategy INSTANCE = new DerivedEqualsStrategy();

	public static DerivedEqualsStrategy getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Extend the {@link DefaultEqualsStrategy} {@link Object} method to dispatch
	 * strategies for {@link Comparable} and dispatch other types to the super method.
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs instanceof OffsetTime && rhs instanceof OffsetTime)
			return equalsInternal(lhsLocator, rhsLocator, (OffsetTime) lhs, (OffsetTime) rhs);
		else if (lhs instanceof OffsetDateTime && rhs instanceof OffsetDateTime)
			return equalsInternal(lhsLocator, rhsLocator, (OffsetDateTime) lhs, (OffsetDateTime) rhs);
		else if (lhs instanceof Comparable<?> && rhs instanceof Comparable<?> && Objects.equals(lhs.getClass(), rhs.getClass()))
			return equalsInternal(lhsLocator, rhsLocator, (Comparable<Object>) lhs, (Comparable<Object>) rhs);
		else if (lhs instanceof XMLGregorianCalendar && rhs instanceof XMLGregorianCalendar)
			return equalsInternal(lhsLocator, rhsLocator, (XMLGregorianCalendar) lhs, (XMLGregorianCalendar) rhs);
		else
			return super.equalsInternal(lhsLocator, rhsLocator, lhs, rhs);
	}

	/**
	 * Provide a strategy to equate {@link Comparable} objects using their <em>compareTo</em>
	 * method.
	 *
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side {@link Comparable} object.
	 * @param rhs The right hand side {@link Comparable} object.
	 *
	 * @return True when objects are equal by comparison; otherwise, false.
	 */
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator,
		Comparable<Object> lhs, Comparable<Object> rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, (lhs == null) ? (rhs == null) : (lhs.compareTo(rhs) == 0));
	}

	/**
	 * Provide a strategy to equate {@link OffsetTime} pairs equating instances.
	 *
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side {@link OffsetTime}.
	 * @param rhs The right hand side {@link OffsetTime}.
	 *
	 * @return True when the {@code OffsetTime} values are equal by {@code Instance}; otherwise, false.
	 */
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator,
		OffsetTime lhs, OffsetTime rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs.isEqual(rhs));
	}

	/**
	 * Provide a strategy to equate {@link OffsetDateTime} pairs equating instances.
	 *
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side {@link OffsetDateTime}.
	 * @param rhs The right hand side {@link OffsetDateTime}.
	 *
	 * @return True when the {@code OffsetDateTime} values are equal by {@code Instance}; otherwise, false.
	 */
	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator,
		OffsetDateTime lhs, OffsetDateTime rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs.isEqual(rhs));
	}

	/**
	 * Provide a strategy to equate {@link XMLGregorianCalendar} pairs equating by time in milliseconds.
	 *
	 * <p><b>Note:</b> {@code XMLGregorianCalendar.equals()} is notoriously strict and counter-intuitive.
	 * It only returns true if two dates are completely identical in representation.
	 * For example, {@code 2026-05-24T12:00:00Z} and {@code 2026-05-24T13:00:00+01:00}
	 * represent the exact same moment in time, but {@code equals()} returns false
	 * because their timezone fields differ.</p>
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
		long lhsMilliseconds = lhs.toGregorianCalendar().getTimeInMillis();
		long rhsMilliseconds = rhs.toGregorianCalendar().getTimeInMillis();
		return equals(lhsLocator, rhsLocator, lhsMilliseconds, rhsMilliseconds);
	}
}
