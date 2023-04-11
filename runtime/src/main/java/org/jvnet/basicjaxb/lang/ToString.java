package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;

/**
 * Interface to append a {@link StringBuilder} using {@link ToStringStrategy} and preserve an
 * {@link ObjectLocator} path.
 */
public interface ToString
{
	public StringBuilder append(ObjectLocator locator, StringBuilder stringBuilder, ToStringStrategy toStringStrategy);
	public StringBuilder appendFields(ObjectLocator locator, StringBuilder stringBuilder, ToStringStrategy toStringStrategy);
}
