package org.jvnet.basicjaxb.plugin.codegenerator;

import java.util.Collection;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JType;

/**
 * Concrete extension of {@link AbstractCodeGenerator} for {@link Double} types.
 *
 * @param <A> The generic type of the plugin arguments.
 */
public class DoubleCodeGenerator<A extends Arguments<A>> extends AbstractCodeGenerator<A>
{
	/**
	 * Construct using {@link CodeGenerator} and {@link CodeGenerationImplementor} instances.
	 * Delegate construction to {@link AbstractCodeGenerator}.
	 * 
	 * @param codeGenerator A {@link CodeGenerator} instance
	 * @param implementor A {@link CodeGenerationImplementor} instance.
	 */
	public DoubleCodeGenerator(CodeGenerator<A> codeGenerator, CodeGenerationImplementor<A> implementor)
	{
		super(codeGenerator, implementor);
	}

	/**
	 * Generate code for a XJC plugin using the plugin's arguments for the current target field.
	 * Delegates to the {@link CodeGenerationImplementor}'s <em>onDouble()</em> method.
	 */
	@Override
	public void generate(JBlock block, JType type, Collection<JType> possibleTypes, boolean isAlwaysSet, A arguments)
	{
		getImplementor().onDouble(arguments, block, isAlwaysSet);
	}
}