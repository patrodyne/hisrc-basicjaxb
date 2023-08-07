package org.jvnet.basicjaxb.plugin.codegenerator;

import static java.util.Objects.requireNonNull;

import com.sun.codemodel.JCodeModel;

public abstract class AbstractCodeGenerationImplementor<A extends Arguments<A>>
	implements CodeGenerationImplementor<A>
{
	private final JCodeModel codeModel;
	@Override
	public JCodeModel getCodeModel()
	{
		return codeModel;
	}

	public AbstractCodeGenerationImplementor(JCodeModel codeModel)
	{
		this.codeModel = requireNonNull(codeModel);
	}
}
