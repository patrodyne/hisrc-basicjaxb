package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;

public interface Equals
{
	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object that,
		EqualsStrategy equalsStrategy);
}
