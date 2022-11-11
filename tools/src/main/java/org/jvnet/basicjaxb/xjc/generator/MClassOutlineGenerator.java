package org.jvnet.basicjaxb.xjc.generator;

import org.jvnet.basicjaxb.xjc.outline.MClassOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;
import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;

import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MClassOutlineGenerator {

	public MClassOutline generate(MPackageOutline parent, MModelInfo<NType, NClass> modelInfo,
			MClassInfo<NType, NClass> classInfo);

}
