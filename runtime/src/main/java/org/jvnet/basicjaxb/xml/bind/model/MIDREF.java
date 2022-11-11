package org.jvnet.basicjaxb.xml.bind.model;

public interface MIDREF<T, C extends T> extends MTypeInfo<T, C> {

	public MTypeInfo<T, C> getValueTypeInfo();

}
