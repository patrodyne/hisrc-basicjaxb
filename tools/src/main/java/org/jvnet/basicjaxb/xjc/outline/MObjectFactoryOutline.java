package org.jvnet.basicjaxb.xjc.outline;

import com.sun.codemodel.JDefinedClass;

public interface MObjectFactoryOutline extends MChildOutline, MPackagedOutline {

	public JDefinedClass getCode();
}
