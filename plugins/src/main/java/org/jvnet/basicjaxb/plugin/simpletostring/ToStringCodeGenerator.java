package org.jvnet.basicjaxb.plugin.simpletostring;

import org.apache.commons.lang3.Validate;
import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerationAbstraction;

import com.sun.codemodel.JCodeModel;

public class ToStringCodeGenerator extends CodeGenerationAbstraction<ToStringArguments>
{
	public ToStringCodeGenerator(JCodeModel codeModel)
	{
		super(new ToStringCodeGenerationImplementor(Validate.notNull(codeModel)));
	}
}
