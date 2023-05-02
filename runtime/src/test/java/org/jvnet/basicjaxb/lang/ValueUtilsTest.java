package org.jvnet.basicjaxb.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.jvnet.basicjaxb.lang.ValueUtils.toDouble;
import static org.jvnet.basicjaxb.lang.ValueUtils.toFloat;

import org.junit.jupiter.api.Test;

public class ValueUtilsTest
{
	@Test
	public void testToDouble()
	{
		assertEquals(0.0, toDouble("0"));
		assertEquals(-0.0, toDouble("-0"));
		assertEquals(-10000.0, toDouble("-1E4"));
		assertEquals(0.1278, toDouble("12.78e-2"));
		assertEquals(1.26743233E15, toDouble("1267.43233E12"));
		assertEquals(Double.NaN, toDouble("NaN"));
		assertEquals(Double.POSITIVE_INFINITY, toDouble("INF"));
		assertEquals(Double.NEGATIVE_INFINITY, toDouble("-INF"));
	}

	@Test
	public void testToFloat()
	{
		assertEquals(0.0f, toFloat("0"));
		assertEquals(-0.0f, toFloat("-0"));
		assertEquals(-10000.0f, toFloat("-1E4"));
		assertEquals(0.1278f, toFloat("12.78e-2"));
		assertEquals(1.26743233E15f, toFloat("1267.43233E12"));
		assertEquals(Float.NaN, toFloat("NaN"));
		assertEquals(Float.POSITIVE_INFINITY, toFloat("INF"));
		assertEquals(Float.NEGATIVE_INFINITY, toFloat("-INF"));
	}
}
