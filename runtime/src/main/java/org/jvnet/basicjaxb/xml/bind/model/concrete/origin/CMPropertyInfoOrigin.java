package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MPropertyInfoOrigin;

public class CMPropertyInfoOrigin<T, C, PI extends PropertyInfo<T, C>> implements MPropertyInfoOrigin,
		PropertyInfoOrigin<T, C, PI> {

	private final PI source;

	public CMPropertyInfoOrigin(PI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public PI getSource() {
		return source;
	}

}
