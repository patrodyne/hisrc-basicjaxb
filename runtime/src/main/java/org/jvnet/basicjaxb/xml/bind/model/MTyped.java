package org.jvnet.basicjaxb.xml.bind.model;

public interface MTyped<T, C extends T> {

	public MTypeInfo<T, C> getTypeInfo();
}
