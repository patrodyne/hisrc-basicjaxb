package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;

public interface HashCodeStrategy
{
	public boolean isDebugEnabled();
	public boolean isTraceEnabled();

	public int hashCode(ObjectLocator locator, int hashCode, boolean value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, byte value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, char value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, double value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, float value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, int value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, long value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, short value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, Object value, boolean valueSet);
	
	public int hashCode(ObjectLocator locator, int hashCode, boolean[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, byte[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, char[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, double[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, float[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, int[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, long[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, short[] value, boolean valueSet);
	public int hashCode(ObjectLocator locator, int hashCode, Object[] value, boolean valueSet);
}
