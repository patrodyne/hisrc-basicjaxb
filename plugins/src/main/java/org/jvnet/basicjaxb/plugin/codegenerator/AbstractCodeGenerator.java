package org.jvnet.basicjaxb.plugin.codegenerator;

import static java.util.Objects.requireNonNull;

import com.sun.codemodel.JCodeModel;

/**
 * Abstract implementation of {@link CodeGenerator} interface 
 * 
 * @param <A> The plugin arguments generic type.
 */
public abstract class AbstractCodeGenerator<A extends Arguments<A>>
	implements CodeGenerator<A>
{
	// Represents the code generator instance.
	private final CodeGenerator<A> codeGenerator;
	/**
	 * Access the {@link CodeGenerator} instance implementation.
	 * 
	 * @return The {@link CodeGenerator} instance implementation.
	 */
	public CodeGenerator<A> getCodeGenerator()
	{
		return codeGenerator;
	}

	private final CodeGenerationImplementor<A> implementor;
	public CodeGenerationImplementor<A> getImplementor()
	{
		return implementor;
	}

	// Represents the code model to use.
	private final JCodeModel codeModel;
	/**
	 * Access the {@link JCodeModel} instance for this code generator.
	 * 
	 * @return The {@link JCodeModel} instance for this code generator.
	 */
	public JCodeModel getCodeModel()
	{
		return codeModel;
	}

	/**
	 * Construct with a {@link CodeGenerator} instance and a {link CodeGenerationImplementor} instance.
	 * Infers {@link JCodeModel} instance from the {link CodeGenerationImplementor} instance.
	 * 
	 * @param codeGenerator The required {@link CodeGenerator} implementation.
	 * @param implementor The required {link CodeGenerationImplementor} instance.
	 */
	public AbstractCodeGenerator(CodeGenerator<A> codeGenerator, CodeGenerationImplementor<A> implementor)
	{
		this.codeGenerator = requireNonNull(codeGenerator);
		this.implementor = requireNonNull(implementor);
		this.codeModel = implementor.getCodeModel();
	}
}