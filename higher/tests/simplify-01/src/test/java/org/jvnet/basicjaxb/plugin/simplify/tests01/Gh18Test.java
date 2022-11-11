package org.jvnet.basicjaxb.plugin.simplify.tests01;

import org.junit.jupiter.api.Test;

public class Gh18Test {

	@Test
	public void compiles() {
		final Gh18 item = new Gh18();
		item.getA();
		item.getChildren();
		item.getFeet();
		item.getFoos();
	}
}
