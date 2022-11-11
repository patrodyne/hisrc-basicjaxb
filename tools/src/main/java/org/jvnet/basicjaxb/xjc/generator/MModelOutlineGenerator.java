package org.jvnet.basicjaxb.xjc.generator;

import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;

import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MModelOutlineGenerator {

	public MModelOutline generate(MModelInfo<NType, NClass> modelInfo);

}
