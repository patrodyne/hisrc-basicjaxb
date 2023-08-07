package org.jvnet.basicjaxb.plugin.simpleequals;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerationAbstraction;

import com.sun.codemodel.JCodeModel;

public class EqualsCodeGenerator extends CodeGenerationAbstraction<EqualsArguments>
{
	public EqualsCodeGenerator(JCodeModel codeModel)
	{
		super(new EqualsCodeGenerationImplementor(requireNonNull(codeModel)));
	}
}
