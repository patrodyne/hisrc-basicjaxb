package org.jvnet.basicjaxb.plugin.codegenerator;

import java.util.Collection;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JType;

/**
 * Concrete extension of {@link AbstractCodeGenerator} for {@link Long} types.
 *
 * @param <A> The generic type of the plugin arguments.
 */
public class LongCodeGenerator<A extends Arguments<A>> extends AbstractCodeGenerator<A>
{
	/**
	 * Construct using {@link CodeGenerator} and {@link CodeGenerationImplementor} instances.
	 * Delegate construction to {@link AbstractCodeGenerator}.
	 * 
	 * @param codeGenerator A {@link CodeGenerator} instance
	 * @param implementor A {@link CodeGenerationImplementor} instance.
	 */
	public LongCodeGenerator(CodeGenerator<A> codeGenerator, CodeGenerationImplementor<A> implementor)
	{
		super(codeGenerator, implementor);
	}

	/**
	 * Generate code for a XJC plugin using the plugin's arguments for the current target field.
	 * Delegates to the {@link CodeGenerationImplementor}'s <em>onLong()</em> method.
	 */
	@Override
	public void generate(JBlock block, JType type, Collection<JType> possibleTypes, boolean isAlwaysSet, A arguments)
	{
		getImplementor().onLong(arguments, block, isAlwaysSet);
	}
}