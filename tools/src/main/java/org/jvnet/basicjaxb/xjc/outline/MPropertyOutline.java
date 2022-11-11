package org.jvnet.basicjaxb.xjc.outline;

import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTargeted;

import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public interface MPropertyOutline extends
		MTargeted<MPropertyInfo<NType, NClass>>, MPropertyAccessorFactory {

	public MClassOutline getClassOutline();

}
