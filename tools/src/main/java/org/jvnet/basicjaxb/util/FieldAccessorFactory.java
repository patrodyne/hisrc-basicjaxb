package org.jvnet.basicjaxb.util;

import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;

import com.sun.codemodel.JExpression;
import com.sun.tools.xjc.outline.FieldOutline;

/**
 * Interface to create a field accessor factory.
 */
public interface FieldAccessorFactory
{
	/**
	 * Create a {@link FieldAccessorEx} from given {@link FieldOutline} and {@link JExpression}
	 * instances.
	 * 
	 * @param fieldOutline The {@link FieldOutline} instance for the factory.
	 * @param targetObject The {@link JExpression} instance for the factory.
	 * 
	 * @return A new {@link FieldAccessorEx} instance.
	 */
	public FieldAccessorEx createFieldAccessor(FieldOutline fieldOutline, JExpression targetObject);
}
