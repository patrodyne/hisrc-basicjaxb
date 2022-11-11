package org.jvnet.basicjaxb.lang;

import org.jvnet.basicjaxb.locator.ObjectLocator;

@Deprecated
public interface HashCode {

	public int hashCode(ObjectLocator locator, HashCodeStrategy hashCodeStrategy);
}
