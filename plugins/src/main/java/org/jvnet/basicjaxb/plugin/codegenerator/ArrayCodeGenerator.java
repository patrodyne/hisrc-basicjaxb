package org.jvnet.basicjaxb.plugin.codegenerator;

import java.util.Collection;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JType;

/**
 * Concrete implementation of {@link AbstractCodeGenerator}.
 * 
 * @param <A> The arguments generic type.
 */
public class ArrayCodeGenerator<A extends Arguments<A>> extends AbstractCodeGenerator<A>
{
	/**
	 * Construct with a {@link CodeGenerator} and {@link CodeGenerationImplementor}.
	 * Delegates to super class.
	 * 
	 * @param codeGenerator The {@link CodeGenerator} instance.
	 * @param implementor The {@link CodeGenerationImplementor} instance.
	 */
	public ArrayCodeGenerator(CodeGenerator<A> codeGenerator, CodeGenerationImplementor<A> implementor)
	{
		super(codeGenerator, implementor);
	}

	/**
	 * Delegate to {@link CodeGenerationImplementor} instance.
	 */
	@Override
	public void generate(JBlock block, JType type, Collection<JType> possibleTypes, boolean isAlwaysSet, A arguments)
	{
		getImplementor().onArray(block, isAlwaysSet, arguments);
	}
}