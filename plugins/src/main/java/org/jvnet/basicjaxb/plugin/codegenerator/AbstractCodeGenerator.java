package org.jvnet.basicjaxb.plugin.codegenerator;

import static java.util.Objects.requireNonNull;

import com.sun.codemodel.JCodeModel;

public abstract class AbstractCodeGenerator<A extends Arguments<A>>
	implements CodeGenerator<A>
{
	private final CodeGenerator<A> codeGenerator;
	public CodeGenerator<A> getCodeGenerator()
	{
		return codeGenerator;
	}

	private final CodeGenerationImplementor<A> implementor;
	public CodeGenerationImplementor<A> getImplementor()
	{
		return implementor;
	}

	private final JCodeModel codeModel;
	public JCodeModel getCodeModel()
	{
		return codeModel;
	}

	public AbstractCodeGenerator(CodeGenerator<A> codeGenerator, CodeGenerationImplementor<A> implementor)
	{
		this.codeGenerator = requireNonNull(codeGenerator);
		this.implementor = requireNonNull(implementor);
		this.codeModel = implementor.getCodeModel();
	}
}