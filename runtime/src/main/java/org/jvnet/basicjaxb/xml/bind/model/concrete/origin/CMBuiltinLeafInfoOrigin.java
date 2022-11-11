package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import org.jvnet.basicjaxb.lang.Validate;
import org.jvnet.basicjaxb.xml.bind.model.origin.MBuiltinLeafInfoOrigin;

import org.glassfish.jaxb.core.v2.model.core.BuiltinLeafInfo;

public class CMBuiltinLeafInfoOrigin<T, C, BLI extends BuiltinLeafInfo<T, C>>
		implements MBuiltinLeafInfoOrigin, BuiltinLeafInfoOrigin<T, C, BLI> {

	private final BLI source;

	public CMBuiltinLeafInfoOrigin(BLI source) {
		Validate.notNull(source);
		this.source = source;
	}

	public BLI getSource() {
		return source;
	}

}
