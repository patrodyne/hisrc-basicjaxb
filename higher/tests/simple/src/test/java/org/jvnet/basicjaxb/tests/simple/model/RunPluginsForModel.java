package org.jvnet.basicjaxb.tests.simple.model;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.tests.simple.RunPlugins;

@Disabled
@Order(1)
public class RunPluginsForModel extends RunPlugins
{
	@BeforeEach
	public void setUp()
	{
		System.setProperty("javax.xml.accessExternalSchema", "all");
	}

	@Test
	public void compileMainSchema()
		throws Exception
	{
		new File("target/generated-sources/xjc").mkdirs();
		URL schema = getClass().getResource("/organization.xsd");
		URL binding = getClass().getResource("/organization.xjb");

		final String[] arguments = new String[] {
			"-xmlschema", schema.toExternalForm(),
			"-b", binding.toExternalForm(),
			"-d", "target/generated-sources/xjc",
			"-extension",
			"-Xannotate",
			"-XsimpleHashCode",
			"-XsimpleEquals",
			"-XsimpleToString",
			"-XsimpleToString-showFieldNames=true",
			"-XsimpleToString-showChildItems=true",
			"-XsimpleToString-fullClassName=false"
		};
		runPlugins(arguments);
	}
}
