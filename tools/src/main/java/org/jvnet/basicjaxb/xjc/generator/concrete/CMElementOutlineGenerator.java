package org.jvnet.basicjaxb.xjc.generator.concrete;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.xjc.generator.MElementOutlineGenerator;
import org.jvnet.basicjaxb.xjc.outline.MElementOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;
import org.jvnet.basicjaxb.xjc.outline.concrete.CMElementOutline;
import org.jvnet.basicjaxb.xml.bind.model.MElementInfo;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;

import com.sun.tools.xjc.model.CElementInfo;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.ElementOutline;
import com.sun.tools.xjc.outline.Outline;

public class CMElementOutlineGenerator implements MElementOutlineGenerator {

	private final Outline outline;
	private final CElementInfo elementInfo;

	public CMElementOutlineGenerator(Outline outline, CElementInfo elementInfo) {
		requireNonNull(outline);
		requireNonNull(elementInfo);
		this.outline = outline;
		this.elementInfo = elementInfo;
	}

	@Override
	public MElementOutline generate(MPackageOutline parent,
			MModelInfo<NType, NClass> modelInfo,
			MElementInfo<NType, NClass> elementInfo) {
		final ElementOutline elementOutline = outline
				.getElement(this.elementInfo);
		if (elementOutline != null) {
			return new CMElementOutline(parent.getParent(), parent,
					elementInfo, elementOutline.implClass);
		} else {
			return null;
		}
	}

}
