package org.jvnet.basicjaxb.xjc.generator;

import org.jvnet.basicjaxb.xjc.outline.MObjectFactoryOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPackageInfo;

import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MObjectFactoryOutlineGenerator {

	public MObjectFactoryOutline generate(MPackageOutline parent,
			MModelInfo<NType, NClass> modelInfo, MPackageInfo packageInfo);

}
