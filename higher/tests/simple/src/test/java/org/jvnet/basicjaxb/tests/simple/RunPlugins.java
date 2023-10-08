package org.jvnet.basicjaxb.tests.simple;

import java.io.IOException;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.ConsoleErrorReporter;
import com.sun.tools.xjc.ModelLoader;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;

/**
 * Run XJC plugins.
 * 
 * @author Rick O'Sullivan
 */
public class RunPlugins
{
	/**
	 * Run XJC plugins for the given arguments.
	 * 
	 * @param arguments XJC options
	 * 
	 * @throws BadCommandLineException
	 * @throws IOException
	 */
	public void runPlugins(final String[] arguments)
		throws BadCommandLineException, IOException
	{
		// Parse arguments into XJC options.
		Options options = new Options();
		options.parseArguments(arguments);
		
		// Output errors to console.
		ConsoleErrorReporter receiver = new ConsoleErrorReporter();
		
		// Load schemas from options into a language neutral Model and receive errors.
		// Then Fully-generate the source code into the given model.
		Model model = ModelLoader.load(options, new JCodeModel(), receiver);
		model.generateCode(options, receiver);
		
		// Creates a configured CodeWriter that produces files into the specified directory.
		// Then generate the Java source code into the target path.
		CodeWriter cw = options.createCodeWriter();
		model.codeModel.build(cw);
	}
}
