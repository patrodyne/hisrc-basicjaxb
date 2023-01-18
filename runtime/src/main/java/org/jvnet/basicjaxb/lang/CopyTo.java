package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;

public interface CopyTo
{
	public Object createNewInstance();
	public Object copyTo(Object target);
	public Object copyTo(ObjectLocator locator, Object target, CopyStrategy copyStrategy);
	public Object clone();
}
