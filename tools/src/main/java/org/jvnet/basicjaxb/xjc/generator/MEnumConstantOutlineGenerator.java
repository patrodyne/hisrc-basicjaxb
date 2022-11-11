package org.jvnet.basicjaxb.xjc.generator;

import org.jvnet.basicjaxb.xjc.outline.MEnumConstantOutline;
import org.jvnet.basicjaxb.xjc.outline.MEnumOutline;
import org.jvnet.basicjaxb.xml.bind.model.MEnumConstantInfo;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;

import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MEnumConstantOutlineGenerator {

	public MEnumConstantOutline generate(MEnumOutline parent,
			MModelInfo<NType, NClass> modelInfo,
			MEnumConstantInfo<NType, NClass> enumConstantInfo);

}
