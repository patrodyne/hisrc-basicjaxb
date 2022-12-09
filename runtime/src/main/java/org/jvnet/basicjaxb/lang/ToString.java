package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;

public interface ToString
{
	public StringBuilder append(ObjectLocator locator, StringBuilder stringBuilder, ToStringStrategy toStringStrategy);
	public StringBuilder appendFields(ObjectLocator locator, StringBuilder stringBuilder, ToStringStrategy toStringStrategy);
}
