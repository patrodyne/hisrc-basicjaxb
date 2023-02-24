package org.jvnet.basicjaxb.plugin.util;

import org.jvnet.basicjaxb.plugin.Ignoring;

import com.sun.tools.xjc.outline.FieldOutline;

/**
 * A utility class for {@link FieldOutline} actions.
 */
public class FieldOutlineUtils
{
	/** Private constructor to seal this utility class. */
	private FieldOutlineUtils()
	{
	}

	/**
	 * Filter an array of {@link FieldOutline} 
	 * 
	 * @param fieldOutlines An array of {@link FieldOutline} to be filtered.
	 * @param ignoring An interface to filter XJC model and outline objects.
	 * 
	 * @return A filtered array of {@link FieldOutline}.
	 */
	public static FieldOutline[] filter(final FieldOutline[] fieldOutlines, final Ignoring ignoring)
	{
		// Implement a Predicate to evaluate a FieldOutline for ignoring.
		return ArrayUtils.filter(fieldOutlines, new Predicate<FieldOutline>()
		{
			public boolean evaluate(FieldOutline fieldOutline)
			{
				return !ignoring.isIgnored(fieldOutline);
			}
		}, FieldOutline.class);
	}
}
