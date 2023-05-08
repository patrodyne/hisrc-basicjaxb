package org.jvnet.basicjaxb.lang;

import static org.jvnet.basicjaxb.locator.util.LocatorUtils.item;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.property;

import java.util.Collection;

import org.jvnet.basicjaxb.dom.DOMUtils;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class DefaultEqualsStrategy implements EqualsStrategy
{
	private static DefaultEqualsStrategy INSTANCE = new DefaultEqualsStrategy();

	public static DefaultEqualsStrategy getInstance()
	{
		return INSTANCE;
	}

	private Logger logger = LoggerFactory.getLogger(EqualsStrategy.class);
	@Override
	public Logger getLogger()
	{
		return logger;
	}

	@Override
	public boolean isDebugEnabled()
	{
		return getLogger().isDebugEnabled();
	}

	@Override
	public boolean isTraceEnabled()
	{
		return getLogger().isTraceEnabled();
	}

	/**
	 * Subclasses can override this method to log the debug message, as desired.
	 * 
	 * @param message The debug message of copied values.
	 */
	public void debug(String message)
	{
		getLogger().debug(message);
	}

	/**
	 * Subclasses should override this method to log the trace message, as
	 * desired.
	 * 
	 * @param message The trace message of unequal values.
	 */
	public void trace(String message)
	{
		getLogger().trace(message);
	}

	protected boolean observe(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs, boolean result)
	{
		if (isTraceEnabled())
		{
			trace(result ? "Objects are equal." : "Objects are NOT equal!");
			trace(buildMessage("LHS", lhsLocator, lhs));
			trace(buildMessage("RHS", rhsLocator, rhs));
		}
		else if (isDebugEnabled())
		{
			if (!result)
			{
				debug("Objects are NOT equal!");
				debug(buildMessage("LHS", lhsLocator, lhs));
				debug(buildMessage("RHS", rhsLocator, rhs));
			}
		}
		return result;
	}

	private String buildMessage(String label, ObjectLocator locator, Object obj)
	{
		String value = "null";
		String message = "";
		if (obj != null)
		{
			if (obj instanceof Collection<?>)
				value = obj.getClass().getName() + "[" + ((Collection<?>) obj).size() + "]";
			else if (obj != null && obj.getClass().isArray())
				value = obj.getClass().getName() + "[" + ((Object[]) obj).length + "]";
			else
				value = obj.toString();
		}
		if (locator != null)
			message = label + ": " + "{" + locator.getPathAsString() + "} -> " + value;
		else
			message = label + ": " + "{} -> " + value;
		return message;
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, equalsObject(lhsLocator, rhsLocator, lhs, rhs));
	}

	protected boolean equalsObject(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		Class<?> lhsClass = lhs.getClass();
		if (!lhsClass.isArray())
			return equalsInternal(lhsLocator, rhsLocator, lhs, rhs);
		else if (lhs.getClass() != rhs.getClass())
		{
			// Here when we compare different dimensions, for example: a
			// boolean[][] to a boolean[]
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		// 'Switch' on type of array, to dispatch to the correct handler
		// This handles multi-dimensional arrays of the same depth
		else if (lhs instanceof long[])
			return equals(lhsLocator, rhsLocator, (long[]) lhs, (long[]) rhs);
		else if (lhs instanceof int[])
			return equals(lhsLocator, rhsLocator, (int[]) lhs, (int[]) rhs);
		else if (lhs instanceof short[])
			return equals(lhsLocator, rhsLocator, (short[]) lhs, (short[]) rhs);
		else if (lhs instanceof char[])
			return equals(lhsLocator, rhsLocator, (char[]) lhs, (char[]) rhs);
		else if (lhs instanceof byte[])
			return equals(lhsLocator, rhsLocator, (byte[]) lhs, (byte[]) rhs);
		else if (lhs instanceof double[])
			return equals(lhsLocator, rhsLocator, (double[]) lhs, (double[]) rhs);
		else if (lhs instanceof float[])
			return equals(lhsLocator, rhsLocator, (float[]) lhs, (float[]) rhs);
		else if (lhs instanceof boolean[])
			return equals(lhsLocator, rhsLocator, (boolean[]) lhs, (boolean[]) rhs);
		else if (lhs instanceof Equals[])
			return equalsInternal(lhsLocator, rhsLocator, (Equals[]) lhs, (Equals[]) rhs);
		else if (lhs instanceof Enum[])
			return equalsInternal(lhsLocator, rhsLocator, (Enum<?>[]) lhs, (Enum<?>[]) rhs);
		else
		{
			// Not an array of primitives
			return equals(lhsLocator, rhsLocator, (Object[]) lhs, (Object[]) rhs);
		}
	}

	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs instanceof Equals && rhs instanceof Equals)
			return equalsInternal(lhsLocator, rhsLocator, (Equals) lhs, (Equals) rhs);
		else if (lhs instanceof Enum<?> && rhs instanceof Enum<?>)
			return equalsInternal(lhsLocator, rhsLocator, (Enum<?>) lhs, (Enum<?>) rhs);
		else if (lhs instanceof Element && rhs instanceof Element)
			return equalsInternal(lhsLocator, rhsLocator, (Element) lhs, (Element) rhs);
		else
			return lhs.equals(rhs);
	}

	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Enum<?> lhs, Enum<?> rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs instanceof Equals && rhs instanceof Equals)
			return equalsInternal(lhsLocator, rhsLocator, (Equals) lhs, (Equals) rhs);
		else
			return lhs.equals(rhs);
	}

	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Equals lhs, Equals rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		return lhs.equals(lhsLocator, rhsLocator, rhs, this);
	}

	protected boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Element lhs, Element rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		// Ignore the prefix, it may vary between XML instances.
		// Alternative to rhs.isEqualNode(lhs); (com.sun.org.apache.xerces.internal.dom.NodeImpl)
		return DOMUtils.areEqualElements(rhs, lhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean lhs, boolean rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs == rhs);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte lhs, byte rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs == rhs);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char lhs, char rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs == rhs);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double lhs, double rhs)
	{
		return equals(lhsLocator, rhsLocator, Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float lhs, float rhs)
	{
		return equals(lhsLocator, rhsLocator, Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long lhs, long rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs == rhs);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int lhs, int rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs == rhs);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short lhs, short rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs == rhs);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object[] lhs, Object[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	private boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Equals[] lhs, Equals[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equalsInternal(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	private boolean equalsInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Enum<?>[] lhs, Enum<?>[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equalsInternal(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean[] lhs, boolean[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte[] lhs, byte[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char[] lhs, char[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double[] lhs, double[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float[] lhs, float[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long[] lhs, long[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int[] lhs, int[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	protected boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short[] lhs, short[] rhs)
	{
		if (lhs == rhs)
			return observe(lhsLocator, rhsLocator, lhs, rhs, true);
		
		if (lhs == null || rhs == null)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		if (lhs.length != rhs.length)
			return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		
		for (int i = 0; i < lhs.length; ++i)
		{
			if (!equals(item(lhsLocator, i, lhs[i]), item(rhsLocator, i, rhs[i]), lhs[i], rhs[i]))
				return observe(lhsLocator, rhsLocator, lhs, rhs, false);
		}
		
		return observe(lhsLocator, rhsLocator, lhs, rhs, true);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean lhs, boolean rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte lhs, byte rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char lhs, char rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double lhs, double rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs):
			equals( property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float lhs, float rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int lhs, int rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long lhs, long rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short lhs, short rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean[] lhs, boolean[] rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte[] lhs, byte[] rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char[] lhs, char[] rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double[] lhs, double[] rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float[] lhs, float[] rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int[] lhs, int[] rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long[] lhs, long[] rhs,	boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short[] lhs, short[] rhs, boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}

	@Override
	public boolean equals(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object[] lhs, Object[] rhs,	boolean lhsSet, boolean rhsSet)
	{
		return (lhsSet && rhsSet) ? equals(lhsLocator, rhsLocator, lhs, rhs) :
			equals(property(lhsLocator, "isSet", lhsSet), property(rhsLocator, "isSet", rhsSet), lhsSet, rhsSet);
	}
}