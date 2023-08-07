package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementInfoOrigin;

public class CMEnumElementInfoOrigin<T, C, ELI extends EnumLeafInfo<T, C>>
		implements MElementInfoOrigin, EnumLeafInfoOrigin<T, C, ELI> {

	private final ELI source;

	public CMEnumElementInfoOrigin(ELI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public ELI getSource() {
		return source;
	}

}
