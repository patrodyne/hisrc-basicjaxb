package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.EnumConstant;
import org.jvnet.basicjaxb.xml.bind.model.origin.MEnumConstantInfoOrigin;

public class CMEnumConstantInfoOrigin<T, C, ECI extends EnumConstant<T, C>>
		implements MEnumConstantInfoOrigin, EnumConstantOrigin<T, C, ECI> {

	private final ECI source;

	public CMEnumConstantInfoOrigin(ECI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public ECI getSource() {
		return source;
	}

}
