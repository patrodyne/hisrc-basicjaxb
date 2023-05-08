package org.jvnet.basicjaxb.strategy;

import com.sun.tools.xjc.outline.ClassOutline;

public interface ClassOutlineProcessor<T, C>
{
	public T process(C context, ClassOutline classOutline) throws Exception;
}
