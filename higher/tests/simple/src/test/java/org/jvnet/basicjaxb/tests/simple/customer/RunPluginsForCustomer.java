package org.jvnet.basicjaxb.tests.simple.customer;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.tests.simple.RunPlugins;

public class RunPluginsForCustomer extends RunPlugins
{
	@BeforeEach
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
