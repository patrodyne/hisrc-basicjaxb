package org.jvnet.basicjaxb.xml.bind.model;

import org.jvnet.basicjaxb.xml.bind.model.origin.MEnumConstantInfoOrigin;
import org.jvnet.basicjaxb.xml.bind.model.origin.MOriginated;

public interface MEnumConstantInfo<T, C> extends
		MOriginated<MEnumConstantInfoOrigin> {

	public String getLexicalValue();
}
