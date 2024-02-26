package org.jvnet.basicjaxb.plugin.util;

import static org.jvnet.basicjaxb.util.FieldUtils.isConstantField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jvnet.basicjaxb.plugin.Ignoring;

import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * A utility class for {@link FieldOutline} actions.
 */
public class OutlineUtils
{
	/** Private constructor to seal this utility class. */
	private OutlineUtils()
	{
	}

	/**
	 * Filter declared fields for the given {@link ClassOutline} 
	 * 
	 * @param classOutline An XJC class outline with declared fields to be filtered.
	 * @param ignoring An interface to filter XJC model and outline objects.
	 * 
	 * @return A filtered array of {@link FieldOutline}.
	 */
	public static FieldOutline[] filter(final ClassOutline classOutline, final Ignoring ignoring)
	{
		return filter(classOutline.getDeclaredFields(), ignoring);
	}
	
	/**
	 * Filter an array of {@link FieldOutline} to omit ignored or constant fields.
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
			@Override
			public boolean evaluate(FieldOutline fieldOutline)
			{
				boolean notIgnored = !ignoring.isIgnored(fieldOutline);
				boolean notConstant = !isConstantField(fieldOutline);
				return notIgnored && notConstant;
			}
		}, FieldOutline.class);
	}

	/**
	 * Filter ignored {@link ClassOutline}s for the given {@link Outline} 
	 * 
	 * <p>In addition, the array is re-ordered by superclass references.</p>
	 * 
	 * @param outline An XJC outline with class outlines to be filtered.
	 * @param ignoring An interface to filter XJC model and outline objects.
	 * 
	 * @return A filtered array of {@link ClassOutline} ordered by superclass references.
	 */
	public static ClassOutline[] filter(final Outline outline, final Ignoring ignoring)
	{
		Collection<? extends ClassOutline> classes = outline.getClasses();
		ClassOutline[] classOutlines = classes.toArray(new ClassOutline[classes.size()]);
		return filter(classOutlines, ignoring);
	}
	
	/**
	 * Filter an array of {@link ClassOutline} for ignored class outlines.
	 * 
	 * <p>In addition, the array is re-ordered by superclass references.</p>
	 * 
	 * @param classOutlines An array of {@link ClassOutline} to be filtered.
	 * @param ignoring An interface to filter XJC model and outline objects.
	 * 
	 * @return A filtered array of {@link ClassOutline} ordered by superclass references.
	 */
	public static ClassOutline[] filter(final ClassOutline[] classOutlines, final Ignoring ignoring)
	{
		// For inheritance, order by superclass.
		ClassOutline[] orderedOutlines = orderBySuper(classOutlines);
		// Implement a Predicate to evaluate a ClassOutline for ignoring.
		return
			ArrayUtils.filter(orderedOutlines, new Predicate<ClassOutline>()
			{
				@Override
				public boolean evaluate(ClassOutline classOutline)
				{
					return !ignoring.isIgnored(classOutline);
				}
			}, ClassOutline.class);
	}
	
	/**
	 * Order the given array of {@link ClassOutline} by superclass
	 * to facilitate inheritance processing.
	 * 
	 * @param toSort The array to be sorted.
	 * 
	 * @return An array sorted by superclass hierarchy.
	 */
	public static ClassOutline[] orderBySuper(ClassOutline[] toSort)
	{
		List<ClassOutline> toSortList = new ArrayList<>(Arrays.asList(toSort));
		orderBySuper(toSortList);
		return toSortList.toArray(new ClassOutline[toSortList.size()]);
	}
	
	/**
	 * Order the given list of {@link ClassOutline} by superclass
	 * to facilitate inheritance processing.
	 * 
	 * @param toSort The list to be sorted.
	 */
	public static void orderBySuper(List<ClassOutline> toSort)
	{
	    List<ClassOutline> ordered = new ArrayList<>();
		boolean more = true;
	    while ( more && !toSort.isEmpty() )
	    {
	    	more = false;
	        for ( ClassOutline co: toSort )
	        {
	        	ClassOutline sc = co.getSuperClass();
	            if ( (sc == null) || ordered.contains(sc) )
	            {
	                ordered.add(co);
	                more = true;
	            }
	        }
	        toSort.removeAll(ordered);
	    }
	    if ( toSort.isEmpty() )
		    toSort.addAll(ordered);
	    else
	    	toSort.addAll(0, ordered);
	}
	
	/**
	 * Filter an array of {@link EnumOutline} for ignored enum outlines.
	 * 
	 * @param enumOutlines A collection of {@link EnumOutline} to be filtered.
	 * @param ignoring An interface to filter XJC model and outline objects.
	 * 
	 * @return A filtered array of {@link EnumOutline}.
	 */
	public static EnumOutline[] filter(final Collection<EnumOutline> enumOutlines, final Ignoring ignoring)
	{
		// Convert Collection<EnumOutline> to EnumOutline[].
		EnumOutline[] enumOutlineArray = enumOutlines.toArray(new EnumOutline[enumOutlines.size()]);
		// Implement a Predicate to evaluate a EnumOutline for ignoring.
		return
			ArrayUtils.filter(enumOutlineArray, new Predicate<EnumOutline>()
			{
				@Override
				public boolean evaluate(EnumOutline classOutline)
				{
					return !ignoring.isIgnored(classOutline);
				}
			}, EnumOutline.class);
	}
}
