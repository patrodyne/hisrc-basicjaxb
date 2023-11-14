package org.jvnet.basicjaxb.plugin.codegenerator;

import static java.util.Objects.requireNonNull;

import com.sun.codemodel.JCodeModel;

/**
 * Abstract code generation implementation 
 * 
 * @param <A> The arguments generic type.
 */
public abstract class AbstractCodeGenerationImplementor<A extends Arguments<A>>
	implements CodeGenerationImplementor<A>
{
	// Represents the code model for this generator.
	private final JCodeModel codeModel;
	/**
	 * Implement access to the {@link JCodeModel} instance.
	 */
	@Override
	public JCodeModel getCodeModel()
	{
		return codeModel;
	}

	/**
	 * Construct with a {@link JCodeModel} instance.
	 * 
	 * @param codeModel The required {@link JCodeModel} instance.
	 */
	public AbstractCodeGenerationImplementor(JCodeModel codeModel)
	{
		this.codeModel = requireNonNull(codeModel);
	}
}
