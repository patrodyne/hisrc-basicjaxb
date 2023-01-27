package org.jvnet.basicjaxb.lang;

import static org.jvnet.basicjaxb.lang.StringUtils.valueToString;

import java.lang.reflect.Array;

import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.RootObjectLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMergeStrategy implements MergeStrategy
{
	private static final DefaultMergeStrategy INSTANCE = new DefaultMergeStrategy();
	public static DefaultMergeStrategy getInstance()
	{
		return INSTANCE;
	}

	private Logger logger = LoggerFactory.getLogger(MergeStrategy.class);
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
	 * Subclasses can override this method to log the trace message, as desired.
	 * 
	 * @param message The trace message of copied values.
	 */
	public void trace(String message)
	{
		getLogger().trace(message);
	}

	/**
	 * Observe an object and its locator.
	 * 
	 * @param <T> The object's type.
	 * @param side Label for "LHS" or "RHS".
	 * @param locator The object locator.
	 * @param obj The result object.
	 * 
	 * @return The result.
	 */
	protected <T> T observe(String side, ObjectLocator locator, T obj)
	{
		if (isTraceEnabled())
			trace(buildMessage("MERGE (" + side + ")", locator, valueToString(obj)));
		else if (isDebugEnabled())
		{
			if ( locator instanceof RootObjectLocator )
				debug(buildMessage("MERGE (" + side + ")", locator, valueToString(obj)));
		}
		return obj;
	}

	/**
	 * Observe objects and their locators.
	 * 
	 * @param <T> The object's type.
	 * @param lhsLocator The left hand side object locator.
	 * @param rhsLocator The right hand side object locator.
	 * @param lhs The left hand side object.
	 * @param rhs The right hand side object.
	 * @param obj The result object.
	 * 
	 * @return The result object.
	 */
	protected <T> T observe(ObjectLocator lhsLocator, ObjectLocator rhsLocator, T lhs, T rhs, T obj)
	{
		if ( obj != null )
		{
			String value = obj.toString();
			if ( obj.equals(lhs) )
				observe("LHS", lhsLocator, value);
			else if ( obj.equals(rhs) )
				observe("RHS", rhsLocator, value);
			else
				observe("?HS", lhsLocator, value);
		}
		else
		{
			String value = "null";
			if ( lhs == null )
				observe("LHS", lhsLocator, value);
			else if ( rhs == null )
				observe("RHS", rhsLocator, value);
			else
				observe("?HS", lhsLocator, value);
		}
		return obj;
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

	@Override
	public Boolean shouldBeMergedAndSet(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean lhsSet, boolean rhsSet)
	{
		return lhsSet || rhsSet;
	}

	protected Object mergeInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs == null)
			return observe("RHS", rhsLocator, rhs);
		else if (rhs == null)
			return observe("LHS", lhsLocator, lhs);
		else
		{
			if (lhs instanceof MergeFrom)
			{
				final Object newInstance = ((MergeFrom) lhs).createNewInstance();
				((MergeFrom) newInstance).mergeFrom(lhsLocator, rhsLocator, lhs, rhs, this);
				return newInstance;
			}
			else if (rhs instanceof MergeFrom)
			{
				final Object newInstance = ((MergeFrom) rhs).createNewInstance();
				((MergeFrom) newInstance).mergeFrom(lhsLocator, rhsLocator, lhs, rhs, this);
				return newInstance;
			}
			else
				return observe("LHS", lhsLocator, lhs);
		}
	}

	protected Object merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs)
	{
		if (lhs == null)
			return observe("RHS", rhsLocator, rhs);
		if (rhs == null)
			return observe("LHS", lhsLocator, lhs);

		Class<?> lhsClass = lhs.getClass();
		if (!lhsClass.isArray())
			return mergeInternal(lhsLocator, rhsLocator, lhs, rhs);
		else if (lhs.getClass() != rhs.getClass())
		{
			// Here when we compare different dimensions, for example: a
			// boolean[][] to a boolean[]
			return false;
		}
		// 'Switch' on type of array, to dispatch to the correct handler
		// This handles multidimensional arrays of the same depth
		else if (lhs instanceof long[])
			return merge(lhsLocator, rhsLocator, (long[]) lhs, (long[]) rhs);
		else if (lhs instanceof int[])
			return merge(lhsLocator, rhsLocator, (int[]) lhs, (int[]) rhs);
		else if (lhs instanceof short[])
			return merge(lhsLocator, rhsLocator, (short[]) lhs, (short[]) rhs);
		else if (lhs instanceof char[])
			return merge(lhsLocator, rhsLocator, (char[]) lhs, (char[]) rhs);
		else if (lhs instanceof byte[])
			return merge(lhsLocator, rhsLocator, (byte[]) lhs, (byte[]) rhs);
		else if (lhs instanceof double[])
			return merge(lhsLocator, rhsLocator, (double[]) lhs, (double[]) rhs);
		else if (lhs instanceof float[])
			return merge(lhsLocator, rhsLocator, (float[]) lhs, (float[]) rhs);
		else if (lhs instanceof boolean[])
			return merge(lhsLocator, rhsLocator, (boolean[]) lhs, (boolean[]) rhs);
		else
		{
			// Not an array of primitives
			return merge(lhsLocator, rhsLocator, (Object[]) lhs, (Object[]) rhs);
		}
	}

	protected long merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long lhs, long rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs != 0 ? lhs : rhs);
	}

	protected int merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int lhs, int rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs != 0 ? lhs : rhs);
	}

	protected short merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short lhs, short rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs != 0 ? lhs : rhs);
	}

	protected char merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char lhs, char rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs != 0 ? lhs : rhs);
	}

	protected byte merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte lhs, byte rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs != 0 ? lhs : rhs);
	}

	protected double merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double lhs, double rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs != 0 ? lhs : rhs);
	}

	protected float merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float lhs, float rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs != 0 ? lhs : rhs);
	}

	protected boolean merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean lhs, boolean rhs)
	{
		return observe(lhsLocator, rhsLocator, lhs, rhs, lhs ? lhs : rhs);
	}

	private <T> T mergeArray(ObjectLocator lhsLocator, ObjectLocator rhsLocator, T lhs, T rhs)
	{
		if (lhs != null)
		{
			if (rhs != null)
				return observe(lhsLocator, rhsLocator, lhs, rhs, Array.getLength(lhs) > 0 ? lhs : rhs);
			else
				return observe("LHS", lhsLocator, lhs);
		}
		else
			return observe("RHS", rhsLocator, rhs);
	}
	
	protected Object[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object[] lhs, Object[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected long[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long[] lhs, long[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected int[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int[] lhs, int[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected short[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short[] lhs, short[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected char[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char[] lhs, char[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected byte[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte[] lhs, byte[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected double[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double[] lhs, double[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected float[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float[] lhs, float[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	protected boolean[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean[] lhs, boolean[] rhs)
	{
		return mergeArray(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public boolean merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean lhs, boolean rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public byte merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte lhs, byte rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public char merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char lhs, char rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public double merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double lhs, double rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public float merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float lhs, float rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public int merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int lhs, int rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public long merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long lhs, long rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public short merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short lhs, short rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public Object merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object lhs, Object rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public boolean[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, boolean[] lhs, boolean[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public byte[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, byte[] lhs, byte[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public char[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, char[] lhs, char[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public double[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, double[] lhs, double[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public float[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, float[] lhs, float[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public int[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, int[] lhs, int[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public long[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, long[] lhs, long[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public short[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, short[] lhs, short[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}

	@Override
	public Object[] merge(ObjectLocator lhsLocator, ObjectLocator rhsLocator, Object[] lhs, Object[] rhs, boolean lhsSet, boolean rhsSet)
	{
		if (lhsSet && !rhsSet)
			return observe("LHS", lhsLocator, lhs);
		else if (!lhsSet && rhsSet)
			return observe("RHS", rhsLocator, rhs);
		else
			return merge(lhsLocator, rhsLocator, lhs, rhs);
	}
}
