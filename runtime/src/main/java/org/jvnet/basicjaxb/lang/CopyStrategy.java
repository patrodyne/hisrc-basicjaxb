package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.slf4j.Logger;

/**
 * Reflection-free copy(...) deep copying.
 */
public interface CopyStrategy
{
	public Logger getLogger();
	public boolean isDebugEnabled();
	public boolean isTraceEnabled();

	public Boolean shouldBeCopiedAndSet(ObjectLocator locator, boolean valueSet);

	public boolean copy(ObjectLocator locator, boolean value, boolean valueSet);
	public byte copy(ObjectLocator locator, byte value, boolean valueSet);
	public char copy(ObjectLocator locator, char value, boolean valueSet);
	public double copy(ObjectLocator locator, double value, boolean valueSet);
	public float copy(ObjectLocator locator, float value, boolean valueSet);
	public int copy(ObjectLocator locator, int value, boolean valueSet);
	public long copy(ObjectLocator locator, long value, boolean valueSet);
	public short copy(ObjectLocator locator, short value, boolean valueSet);
	public Object copy(ObjectLocator locator, Object value, boolean valueSet);

	public Object copyIdRef(ObjectLocator locator, Object value, boolean valueSet);

	public boolean[] copy(ObjectLocator locator, boolean[] value, boolean valueSet);
	public byte[] copy(ObjectLocator locator, byte[] value, boolean valueSet);
	public char[] copy(ObjectLocator locator, char[] value, boolean valueSet);
	public double[] copy(ObjectLocator locator, double[] value, boolean valueSet);
	public float[] copy(ObjectLocator locator, float[] value, boolean valueSet);
	public int[] copy(ObjectLocator locator, int[] value, boolean valueSet);
	public long[] copy(ObjectLocator locator, long[] value, boolean valueSet);
	public short[] copy(ObjectLocator locator, short[] value, boolean valueSet);
	public Object[] copy(ObjectLocator locator, Object[] value, boolean valueSet);
}
