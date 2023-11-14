package org.jvnet.basicjaxb.plugin.codegenerator;

import java.util.Collection;
import java.util.ListIterator;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JType;

import jakarta.xml.bind.JAXBElement;

/**
 * Interface for plugin argument(s) methods. The arguments
 * represent the plugin state for <em>each</em> field generation.
 * 
 * @param <A> The arguments generic plugin type.
 */
public interface Arguments<A extends Arguments<A>>
{
	/**
	 * Does the target field for this {@link Arguments} instance have its value set.
	 * 
	 * @param block The current {@link JBlock} instance.
	 * @param isAlwaysSet Is the field value known to always be set.
	 * @param checkForNullRequired The flag to check for null values.
	 * 
	 * @return A current or new {@link JBlock} instance.
	 */
	public JBlock ifHasSetValue(JBlock block, boolean isAlwaysSet, boolean checkForNullRequired);

	/**
	 * Is the target field for this {@link Arguments} instance an instance of the
	 * given {@link JType} reference.
	 * 
	 * @param type The references {@link JType} instance.
	 * 
	 * @return An {@link JExpression} to determine if the target field is an
	 *         <code>instanceof</code> the referenced {@link JType} instance. 
	 */
	public JExpression _instanceof(JType type);

    /**
     * JCast constructor 
     *
     * @param type
     *        JType to which the expression is cast
     *
     * @param object
     *        JExpression for the object upon which
     *        the cast is applied
     */
	
	/**
	 * Add a local variable declaration to this block to cast
	 * the target field value to the given {@link JType} reference.
	 * 
	 * @param suffix The morpheme added at the end of the casted target field name.
	 * @param block The {@link JBlock} for the casted variable declaration.
	 * @param type The {@link JType} reference for the cast.
	 * @param suppressWarnings A flag to generate a {@link SuppressWarnings} annotation.
	 * 
	 * @return The plugin's {@link Arguments} instance.
	 */
	public A cast(String suffix, JBlock block, JType type, boolean suppressWarnings);

	/**
	 * Spawn a new {@link Arguments} implementation to declare variable(s) in the given
	 * {@link JBlock} to iterate to the next element(s) and generate {@link JExpression}(s)
	 * to determine if the element's value is set.
	 * 
	 * @param subBlock The {@link JBlock} for the next element's variable declaration.
	 * @param elementType The iterated element's {@link JType}.
	 * 
	 * @return The plugin's spawned {@link Arguments} instance.
	 */
	public A element(JBlock subBlock, JType elementType);

	/**
	 * Spawn a new {@link Arguments} implementation to declare variable(s) in the given
	 * {@link JBlock} to instantiate a {@link ListIterator} for the given iterator's
	 * element {@link JType} reference.
	 * 
	 * @param block The {@link JBlock} to receive the declared {@link ListIterator} instance.
	 * @param elementType The {@link JType} of the iterator's element.
	 * 
	 * @return The plugin's spawned {@link Arguments} instance.
	 */
	public A iterator(JBlock block, JType elementType);

	/**
	 * Create a <code>while</code> statement and add it to the given {@link JBlock} instance
	 * to iterate over the target field(s).
	 * 
	 * @param block The {@link JBlock} instance to receive the <code>while</code> statement.
	 * 
	 * @return The new {@link JBlock} instance.
	 */
	public JBlock _while(JBlock block);

	/**
	 * Spawn a new {@link Arguments} implementation to declare variable(s) in the given
	 * {@link JBlock} to instantiate a {@link JAXBElement} property.
	 * 
	 * @param block The {@link JBlock} instance to receive the {@link JAXBElement} property.
	 * @param propertyName The {@link JAXBElement} property name.
	 * @param propertyMethod {@link JAXBElement} property accessor.
	 * @param declarablePropertyType The declared property {@link JType}.
	 * @param propertyType The property {@link JType}, may be same as declarablePropertyType.
	 * @param possiblePropertyTypes The collection of possible property {@link JType}s.
	 * 
	 * @return The plugin's spawned {@link Arguments} instance.
	 */
	public A property(JBlock block, String propertyName, String propertyMethod, 
		JType declarablePropertyType, JType propertyType,
		Collection<JType> possiblePropertyTypes);
}
