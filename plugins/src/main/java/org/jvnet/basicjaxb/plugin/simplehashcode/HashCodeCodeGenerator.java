package org.jvnet.basicjaxb.plugin.simplehashcode;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerationAbstraction;

import com.sun.codemodel.JCodeModel;

public class HashCodeCodeGenerator extends CodeGenerationAbstraction<HashCodeArguments>
{
	public HashCodeCodeGenerator(JCodeModel codeModel)
	{
		super(new HashCodeCodeGenerationImplementor(requireNonNull(codeModel)));
	}
}
