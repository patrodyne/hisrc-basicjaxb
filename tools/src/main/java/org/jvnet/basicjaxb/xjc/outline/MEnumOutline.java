package org.jvnet.basicjaxb.xjc.outline;

import java.util.List;

import org.jvnet.basicjaxb.xml.bind.model.MEnumLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTargeted;

import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MEnumOutline extends MChildOutline, MPackagedOutline,
		MTargeted<MEnumLeafInfo<NType, NClass>> {

	public List<MEnumConstantOutline> getEnumConstantOutlines();

	public JDefinedClass getCode();
}
