package org.jvnet.basicjaxb.plugin.codegenerator;

import java.util.Collection;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JType;

/**
 * Interface to generate code for a XJC plugin.
 * 
 * @param <A> The generic type of the plugin arguments.
 */
public interface CodeGenerator<A extends Arguments<A>>
{
	/**
	 * Generate code for a XJC plugin using the plugin's arguments for the current target field.
	 * 
	 * @param block The {@link JBlock} to receive to generated code.
	 * @param type The {@link JType} of the target field.
	 * @param possibleTypes The possible {@link JType}s of the target field.
	 * @param isAlwaysSet Flag indicating when the value of the target field is always set.
	 * @param arguments An implementation of the plugin's arguments for the current target field.
	 */
	public void generate(JBlock block, JType type, Collection<JType> possibleTypes, boolean isAlwaysSet, A arguments);
}
