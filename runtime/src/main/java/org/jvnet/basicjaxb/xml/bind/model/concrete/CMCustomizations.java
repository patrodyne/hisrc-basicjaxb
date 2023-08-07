package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jvnet.basicjaxb.xml.bind.model.MCustomization;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizations;

public class CMCustomizations implements MCustomizations {

	private final List<MCustomization> customizations = new LinkedList<MCustomization>();
	private final List<MCustomization> unmodifiableCustomizations = Collections
			.unmodifiableList(this.customizations);

	@Override
	public List<MCustomization> getCustomizations() {
		return unmodifiableCustomizations;
	}

	@Override
	public void addCustomization(MCustomization customization) {
		requireNonNull(customization);
		this.customizations.add(customization);
	}

}
