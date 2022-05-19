package org.jvnet.jaxb2_commons.plugin.simpletostring;

import org.apache.commons.lang3.Validate;
import org.jvnet.jaxb2_commons.plugin.codegenerator.CodeGenerationAbstraction;

import com.sun.codemodel.JCodeModel;

public class ToStringCodeGenerator extends CodeGenerationAbstraction<ToStringArguments>
{
	public ToStringCodeGenerator(JCodeModel codeModel)
	{
		super(new ToStringCodeGenerationImplementor(Validate.notNull(codeModel)));
	}
}
