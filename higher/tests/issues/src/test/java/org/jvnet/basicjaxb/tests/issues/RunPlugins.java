package org.jvnet.basicjaxb.tests.issues;

import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.ConsoleErrorReporter;
import com.sun.tools.xjc.ModelLoader;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;

public class RunPlugins
{
	@BeforeEach
	public void setUp()
	{
		System.setProperty("javax.xml.accessExternalSchema", "all");
	}

	@Test
	public void compilesSchema() throws Exception
	{
		new File("target/generated-sources/xjc").mkdirs();

		URL schema = getClass().getResource("/schema.xsd");
		URL binding = getClass().getResource("/binding.xjb");
		
		final String[] arguments = new String[]
		{
			"-xmlschema", schema.toExternalForm(),
			"-b", binding.toExternalForm(),
			"-d", "target/generated-sources/xjc",
			"-extension",
			"-XtoString",
			"-Xequals",
			"-Xequals-equalsStrategyClass=org.jvnet.basicjaxb.tests.issues.IssueJIIB42EqualsStrategy",
			"-XhashCode",
			"-Xcopyable",
			"-Xmergeable",
			"-Xmergeable-mergeStrategyClass=org.jvnet.basicjaxb.lang.JAXBMergeStrategy",
			"-Xinheritance",
			"-Xsetters",
			"-Xsetters-mode=direct",
			"-Xwildcard",
			"-XautoInheritance",
			"-XautoInheritance-xmlRootElementsExtend=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseClass",
			"-XautoInheritance-xmlRootElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceOne",
			"-XautoInheritance-xmlRootElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceTwo",
			"-XautoInheritance-jaxbElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceThree",
			"-XautoInheritance-jaxbElementsImplement=org.jvnet.basicjaxb.tests.issues.IssueJIIB14BaseInterfaceFour",
			"-XenumValue",
			"-Xcustomizations",
			"-Xcustomizations-directory=src/main/resources",
			"-Xcustomizations-verbose=true"
		};

		Options options = new Options();
		options.parseArguments(arguments);
		ConsoleErrorReporter receiver = new ConsoleErrorReporter();
		Model model = ModelLoader.load(options, new JCodeModel(), receiver);
		model.generateCode(options, receiver);
		com.sun.codemodel.CodeWriter cw = options.createCodeWriter();
		model.codeModel.build(cw);
	}
}
