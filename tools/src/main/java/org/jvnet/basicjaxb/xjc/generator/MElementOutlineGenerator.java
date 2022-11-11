package org.jvnet.basicjaxb.xjc.generator;

import org.jvnet.basicjaxb.xjc.outline.MElementOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;
import org.jvnet.basicjaxb.xml.bind.model.MElementInfo;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;

import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MElementOutlineGenerator {

	public MElementOutline generate(MPackageOutline parent,
			MModelInfo<NType, NClass> modelInfo,
			MElementInfo<NType, NClass> elementInfo);

}
