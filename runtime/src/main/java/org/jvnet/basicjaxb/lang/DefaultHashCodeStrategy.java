package org.jvnet.basicjaxb.lang;

import static java.lang.Integer.toHexString;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.item;

import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.RootObjectLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHashCodeStrategy implements HashCodeStrategy
{
	private static final DefaultHashCodeStrategy INSTANCE = new DefaultHashCodeStrategy();

	public static DefaultHashCodeStrategy getInstance()
	{
		return INSTANCE;
	}

	private Logger logger = LoggerFactory.getLogger(HashCodeStrategy.class);
	public Logger getLogger()
	{
		return logger;
	}
	
	public boolean isDebugEnabled()
	{
		return getLogger().isDebugEnabled();
	}

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
	 * Subclasses can override this method to log the trace message, as desired.
	 * 
	 * @param message The trace message of copied values.
	 */
	public void trace(String message)
	{
		getLogger().trace(message);
	}
	
	/**
	 * Observe an int hash and its locator.
	 * 
	 * @param locator The object locator.
	 * @param hash The object.
	 * 
	 * @return The original object.
	 */
	protected int observe(ObjectLocator locator, int hash)
	{
		return observe("HASH", locator, hash);
	}
	
	/**
	 * Observe an int hash and its locator.
	 * 
	 * @param label A prefix for the observation message.
	 * @param locator The object locator.
	 * @param hash The hash integer.
	 * 
	 * @return The original hash.
	 */
	protected int observe(String label, ObjectLocator locator, int hash)
	{
		if ( isTraceEnabled() )
			trace(buildMessage(label, locator, toHexString(hash)));
		else if ( isDebugEnabled() )
		{
			if ( locator instanceof RootObjectLocator )
				debug(buildMessage(label, locator, toHexString(hash)));
		}			
		return hash;
	}

	protected String buildMessage(String label, ObjectLocator locator, String value)
	{
		String message;
		if (locator != null)
			message = label + ": " + "{"+locator.getPathAsString()+"} -> " + value;
		else
			message = label + ": " + "{} -> " + value;
		return message;
	}
	
	private int iConstant;
	public DefaultHashCodeStrategy()
	{
		this.iConstant = 37;
	}

	public DefaultHashCodeStrategy(int multiplierNonZeroOddNumber)
	{
		if (multiplierNonZeroOddNumber == 0)
			throw new IllegalArgumentException("HashCodeStrategy requires a non zero multiplier.");
		if (multiplierNonZeroOddNumber % 2 == 0)
			throw new IllegalArgumentException("HashCodeStrategy requires an odd multiplier.");
		this.iConstant = multiplierNonZeroOddNumber;
	}

	protected int hashCode(ObjectLocator locator, int hashCode, Object object)
	{
		if (object == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			if (object.getClass().isArray() == false)
				return hashCodeInternal(locator, hashCode, object);
			else
			{
				// 'Switch' on type of array, to dispatch to the correct handler
				// Note: This handles multidimensional arrays
				if (object instanceof long[])
					return hashCode(locator, hashCode, (long[]) object);
				else if (object instanceof int[])
					return hashCode(locator, hashCode, (int[]) object);
				else if (object instanceof short[])
					return hashCode(locator, hashCode, (short[]) object);
				else if (object instanceof char[])
					return hashCode(locator, hashCode, (char[]) object);
				else if (object instanceof byte[])
					return hashCode(locator, hashCode, (byte[]) object);
				else if (object instanceof double[])
					return hashCode(locator, hashCode, (double[]) object);
				else if (object instanceof float[])
					return hashCode(locator, hashCode, (float[]) object);
				else if (object instanceof boolean[])
					return hashCode(locator, hashCode, (boolean[]) object);
				else if (object instanceof HashCode[])
					return hashCodeInternal(locator, hashCode, (HashCode[]) object);
				else if (object instanceof Enum[])
					return hashCodeInternal(locator, hashCode, (Enum<?>[]) object);
				else
				{
					// Not an array of primitives
					return hashCode(locator, hashCode, (Object[]) object);
				}
			}
		}
	}

	protected int hashCodeInternal(ObjectLocator locator, int hashCode, Object value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else if (value instanceof HashCode)
			return hashCodeInternal(locator, hashCode, (HashCode) value);
		else if (value instanceof Enum)
			return hashCodeInternal(locator, hashCode, (Enum<?>) value);
		else
			return observe(locator, hashCode * iConstant + value.hashCode());
	}

	protected int hashCodeInternal(ObjectLocator locator, int hashCode, Enum<?> value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else if (value instanceof HashCode)
			return hashCodeInternal(locator, hashCode, (HashCode) value);
		else
			return observe(locator, hashCode * iConstant + value.hashCode());
	}

	protected int hashCodeInternal(ObjectLocator locator, int hashCode, HashCode object)
	{
		if (object == null)
			return observe(locator, hashCode * iConstant);
		else
			return observe(locator, hashCode * iConstant + object.hashCode(locator, this));
	}

	protected int hashCode(ObjectLocator locator, int hashCode, Object[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode * iConstant + 1;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCodeInternal(ObjectLocator locator, int hashCode, Enum<?>[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode * iConstant + 1;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCodeInternal(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCodeInternal(ObjectLocator locator, int hashCode, HashCode[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode * iConstant + 1;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCodeInternal(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, boolean value)
	{
		return observe(locator, hashCode * iConstant + (value ? 0 : 1));
	}

	protected int hashCode(ObjectLocator locator, int hashCode, byte value)
	{
		return observe(locator, hashCode * iConstant + value);
	}

	protected int hashCode(ObjectLocator locator, int hashCode, char value)
	{
		return observe(locator, hashCode * iConstant + value);
	}

	protected int hashCode(ObjectLocator locator, int hashCode, double value)
	{
		return hashCode(locator, hashCode, Double.doubleToLongBits(value));
	}

	protected int hashCode(ObjectLocator locator, int hashCode, float value)
	{
		return hashCode(locator, hashCode, Float.floatToIntBits(value));
	}

	protected int hashCode(ObjectLocator locator, int hashCode, int value)
	{
		return observe(locator, hashCode * iConstant + value);
	}

	protected int hashCode(ObjectLocator locator, int hashCode, long value)
	{
		return observe(locator, hashCode * iConstant + ((int) (value ^ (value >> 32))));
	}

	protected int hashCode(ObjectLocator locator, int hashCode, short value)
	{
		return observe(locator, hashCode * iConstant + value);
	}

	protected int hashCode(ObjectLocator locator, int hashCode, boolean[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, byte[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, char[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, double[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, float[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, int[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, long[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	protected int hashCode(ObjectLocator locator, int hashCode, short[] value)
	{
		if (value == null)
			return observe(locator, hashCode * iConstant);
		else
		{
			int currentHashCode = hashCode;
			for (int i = 0; i < value.length; i++)
				currentHashCode = hashCode(item(locator, i, value[i]), currentHashCode, value[i]);
			return observe(locator, currentHashCode);
		}
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, boolean value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, byte value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, char value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, double value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, float value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, int value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, long value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, short value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, Object value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, boolean[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, byte[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, char[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, double[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, float[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, int[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, long[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, short[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}

	@Override
	public int hashCode(ObjectLocator locator, int hashCode, Object[] value, boolean valueSet)
	{
		return valueSet ? hashCode(locator, hashCode * iConstant, value) : observe(locator, hashCode * iConstant + 1);
	}
}