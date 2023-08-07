package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.BuiltinLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MBuiltinLeafInfoOrigin;

public class CMBuiltinLeafInfoOrigin<T, C, BLI extends BuiltinLeafInfo<T, C>>
		implements MBuiltinLeafInfoOrigin, BuiltinLeafInfoOrigin<T, C, BLI> {

	private final BLI source;

	public CMBuiltinLeafInfoOrigin(BLI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public BLI getSource() {
		return source;
	}

}
