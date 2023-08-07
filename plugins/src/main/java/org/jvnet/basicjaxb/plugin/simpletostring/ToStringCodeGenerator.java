package org.jvnet.basicjaxb.plugin.simpletostring;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerationAbstraction;

import com.sun.codemodel.JCodeModel;

public class ToStringCodeGenerator extends CodeGenerationAbstraction<ToStringArguments>
{
	public ToStringCodeGenerator(JCodeModel codeModel)
	{
		super(new ToStringCodeGenerationImplementor(requireNonNull(codeModel)));
	}
}
