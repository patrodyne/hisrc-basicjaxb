package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;

/**
 * The <b>Equals</b> interface implements a method to determine when two
 * objects are equal. The method includes {@link ObjectLocator}s to track
 * the comparison path and an {@link EqualsStrategy} to determine equality
 * using the object's values.
 */
public interface Equals
{
	/**
	 * Method to determine object equality.
	 * 
	 * @param thisLocator This object's location path.
	 * @param thatLocator That object's location path.
	 * @param that That other object.
	 * @param equalsStrategy Determine equality using this and that object's values.
	 *
	 * @return True when the two object's are equal based on the given strategy.
	 */
	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object that,
		EqualsStrategy equalsStrategy);
}
