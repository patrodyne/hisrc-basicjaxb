package org.example.msi;

import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public abstract class AbstractMessageTest extends AbstractSamplesTest
{
	protected static final org.example.msi.ObjectFactory OF = new org.example.msi.ObjectFactory();
	
	@Override
	protected String getContextPath()
	{
		return ContextUtils.getContextPath(OF.getClass());
	}
}
