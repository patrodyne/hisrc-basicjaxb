package org.jvnet.basicjaxb.xml.bind.model;

import org.jvnet.basicjaxb.xml.bind.model.origin.MPropertyInfoOrigin;

public interface MElementPropertyInfo<T, C extends T> extends MPropertyInfo<T, C>,
		MWrappable, MElementTypeInfo<T, C, MPropertyInfoOrigin> {

}
