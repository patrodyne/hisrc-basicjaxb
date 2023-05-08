package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import org.jvnet.basicjaxb.lang.Validate;
import org.jvnet.basicjaxb.xml.bind.model.origin.MPropertyInfoOrigin;

import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;

public class CMPropertyInfoOrigin<T, C, PI extends PropertyInfo<T, C>> implements MPropertyInfoOrigin,
		PropertyInfoOrigin<T, C, PI> {

	private final PI source;

	public CMPropertyInfoOrigin(PI source) {
		Validate.notNull(source);
		this.source = source;
	}

	@Override
	public PI getSource() {
		return source;
	}

}
