package org.jvnet.basicjaxb.plugin.util;

public interface Predicate<T> {

	public boolean evaluate(T object);

}