package org.example.modulo;

import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public abstract class AbstractRootTest extends AbstractSamplesTest
{
	protected static final org.example.modulo.ia.ObjectFactory OFIA = new org.example.modulo.ia.ObjectFactory();
	protected static final org.example.modulo.pa.ObjectFactory OFPA = new org.example.modulo.pa.ObjectFactory();
	protected static final org.example.modulo.pgr.ObjectFactory OFPGR = new org.example.modulo.pgr.ObjectFactory();
	
	@Override
	protected String getContextPath()
	{
		return ContextUtils.getContextPath(OFPGR.getClass());
	}
}
