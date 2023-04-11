package org.jvnet.basicjaxb.tests.mixed;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.ConsoleErrorReporter;
import com.sun.tools.xjc.ModelLoader;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;

/**
 * Test the fixMixedExtensions plugin.
 */
public class ExecuteMixed
{
	/**
	 * Generate JAXB classes using the fixMixedExtensions plugin.
	 */
	@Test
	public void compilesContext() throws Exception
	{
		new File("target/generated-sources/xjc").mkdirs();

		final String[] arguments = new String[]
		{
		 	"-xmlschema",
			new File("src/main/resources/schema.xsd").toURI().toString(),
			"-d",
			"target/generated-sources/xjc", 
			"-extension",
//			"-XhashCode",
//			"-Xequals",
//			"-XtoString",
//			"-Xcopyable",
//			"-Xmergeable",
			"-XfixMixedExtensions"
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
