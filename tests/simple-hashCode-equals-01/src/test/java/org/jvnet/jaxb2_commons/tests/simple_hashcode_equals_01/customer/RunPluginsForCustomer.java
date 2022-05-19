package org.jvnet.jaxb2_commons.tests.simple_hashcode_equals_01.customer;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.jvnet.jaxb2_commons.tests.simple_hashcode_equals_01.RunPlugins;

public class RunPluginsForCustomer extends RunPlugins
{
	@Before
	public void setUp()
	{
		System.setProperty("javax.xml.accessExternalSchema", "all");
	}

	@Test
	public void compileCustomerSchema() throws Exception
	{
		new File("target/generated-sources/xjc").mkdirs();
		URL schema = getClass().getResource("/customer.xsd");
		URL binding = getClass().getResource("/customer.xjb");
		
		final String[] arguments = new String[] {
			"-xmlschema", schema.toExternalForm(),
			"-b", binding.toExternalForm(),
			"-d", "target/generated-sources/xjc",
			"-extension",
			"-XsimpleHashCode",
			"-XsimpleEquals",
			"-XsimpleToString"
		};
		
		runPlugins(arguments);
	}
}
