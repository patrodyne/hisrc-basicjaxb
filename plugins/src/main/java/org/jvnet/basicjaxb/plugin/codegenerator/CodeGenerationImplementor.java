package org.jvnet.basicjaxb.plugin.codegenerator;

import java.util.Arrays;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;

/**
 * Interface for the plugin's code generation implementor.
 * 
 * @param <A> The generic type of the plugin arguments.
 */
public interface CodeGenerationImplementor<A extends Arguments<A>>
{
	/**
	 * Access the code model for this code generator.
	 * 
	 * @return The {@link JCodeModel} instance for this code generator.
	 */
	public JCodeModel getCodeModel();

	/**
	 * Generate code for {@link Arrays} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onArray(JBlock block, boolean isAlwaysSet, A arguments);

	/**
	 * Generate code for {@link Boolean} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onBoolean(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Byte} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onByte(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Character} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onChar(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Double} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onDouble(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Float} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onFloat(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Integer} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onInt(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Long} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onLong(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Short} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onShort(A arguments, JBlock block, boolean isAlwaysSet);

	/**
	 * Generate code for {@link Object} field types.
	 * 
	 * @param arguments The plugin state for <em>each</em> field generation.
	 * @param block The {@link JBlock} to receive the generated code.
	 * @param isAlwaysSet Flag indicating when the target field value is always set.
	 */
	public void onObject(A arguments, JBlock block, boolean isAlwaysSet);

}
