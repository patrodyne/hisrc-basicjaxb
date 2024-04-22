package org.example.nb;

import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public abstract class AbstractNotebookTest extends AbstractSamplesTest
{
	protected static final org.example.nb.A.ObjectFactory OFA = new org.example.nb.A.ObjectFactory();
	protected static final org.example.nb.B.ObjectFactory OFB = new org.example.nb.B.ObjectFactory();
	
	@Override
	protected String getContextPath()
	{
		return ContextUtils.getContextPath(OFA.getClass() , OFB.getClass());
	}
}
