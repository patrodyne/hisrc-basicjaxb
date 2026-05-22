package org.jvnet.basicjaxb.tests.strategic.cases;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.tests.strategic.RunPlugins;

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

		final String[] arguments = new String[]
		{
			"-extension",
			"-verbose",
			"-no-header",
			"-xmlschema", schema.toExternalForm(),
//			"-b", binding.toExternalForm(),
			"-d", "target/generated-sources/xjc",
			"-extension",
			"-Xannotate",
			"-Xinheritance",
			"-XhashCode",
			"-Xequals",
			"-XtoString",
			"-XhashCode-hashCodeStrategyClass=org.jvnet.basicjaxb.lang.JAXBHashCodeStrategy",
			"-Xequals-equalsStrategyClass=org.jvnet.basicjaxb.lang.JAXBEqualsStrategy",
			"-XtoString-toStringStrategyClass=org.jvnet.basicjaxb.lang.JAXBToStringStrategy"
		};

		runPlugins(arguments);
	}
}
