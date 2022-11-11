package org.jvnet.basicjaxb.tests.simple_hashcode_equals_01.cases;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.tests.simple_hashcode_equals_01.RunPlugins;

public class RunPluginsForCases extends RunPlugins
{
	@BeforeEach
	public void setUp()
	{
		System.setProperty("javax.xml.accessExternalSchema", "all");
	}

	@Test
	public void compileCasesSchema() throws Exception
	{
		new File("target/generated-sources/xjc").mkdirs();
		URL schema = getClass().getResource("/cases.xsd");
		// URL binding = getClass().getResource("/cases.xjb");
		
		final String[] arguments = new String[] {
			"-xmlschema", schema.toExternalForm(),
//			"-b", binding.toExternalForm(),
			"-d", "target/generated-sources/xjc",
			"-extension",
			"-XsimpleHashCode",
			"-XsimpleEquals",
			"-XsimpleToString",
			"-XsimpleToString-fullClassName=true",
			"-XsimpleToString-showFieldNames=true",
			"-XsimpleToString-showChildItems=true"
		};
		
		runPlugins(arguments);
	}
}
