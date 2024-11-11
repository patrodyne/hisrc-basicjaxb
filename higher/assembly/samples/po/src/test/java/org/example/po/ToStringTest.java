package org.example.po;

import java.io.File;

import org.example.po.model.PurchaseOrder;
import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public class ToStringTest extends AbstractSamplesTest
{
	/**
	 * Configure the JAXB context path in AbstractSamplesTest
	 * to include all classes from two packages.
	 */
	@Override
	protected String getContextPath()
	{
		return PurchaseOrder.class.getPackage().getName();
	}
	
	@Override
	protected void checkSample(File sample) throws Exception
	{
		Object object = getUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator rootLocator = new DefaultRootObjectLocator(object);

		ToStringStrategy toStringStrategy = JAXBToStringStrategy.getInstance();
		getLogger().debug(toStringStrategy.append(rootLocator, new StringBuilder(), object).toString());
	}
}
