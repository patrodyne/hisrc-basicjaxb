package org.jvnet.basicjaxb.xjc.model.concrete.origin;

import org.hisrc.xml.xsom.SchemaComponentAware;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.CMElementOrigin;

import com.sun.tools.xjc.model.CElement;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.xml.xsom.XSComponent;

public class XJCCMElementOrigin extends
		CMElementOrigin<NType, NClass, CElement> implements
		SchemaComponentAware {

	public XJCCMElementOrigin(CElement source) {
		super(source);
	}

	@Override
	public XSComponent getSchemaComponent() {
		return getSource().getSchemaComponent();
	}

}
