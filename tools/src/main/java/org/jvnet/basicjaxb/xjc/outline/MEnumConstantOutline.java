package org.jvnet.basicjaxb.xjc.outline;

import org.jvnet.basicjaxb.xml.bind.model.MEnumConstantInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTargeted;

import com.sun.codemodel.JEnumConstant;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MEnumConstantOutline extends
		MTargeted<MEnumConstantInfo<NType, NClass>> {

	public MEnumOutline getEnumOutline();

	public JEnumConstant getCode();

}
