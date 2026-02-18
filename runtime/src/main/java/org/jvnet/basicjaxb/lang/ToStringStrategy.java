package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.slf4j.Logger;

public interface ToStringStrategy
{
	public Logger getLogger();
	public boolean isDebugEnabled();
	public boolean isTraceEnabled();

	public StringBuilder append(ObjectLocator locator, StringBuilder buffer, Object value);

	public StringBuilder appendStart(ObjectLocator parentLocator, Object parent, StringBuilder stringBuilder);
	public StringBuilder appendEnd(ObjectLocator parentLocator, Object parent, StringBuilder stringBuilder);

	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, boolean value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, byte value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, char value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, double value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, float value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, int value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, long value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, short value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, Object value, boolean valueSet);

	public StringBuilder appendIdRef(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, Object value, boolean valueSet);

	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, boolean[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, byte[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, char[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, double[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, float[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, int[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, long[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, short[] value, boolean valueSet);
	public StringBuilder appendField(ObjectLocator parentLocator, Object parent, String fieldName, StringBuilder stringBuilder, Object[] value, boolean valueSet);
}
