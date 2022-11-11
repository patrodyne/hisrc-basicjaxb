package org.jvnet.basicjaxb.xml.bind;

import jakarta.xml.bind.Marshaller;

public interface BeforeMarshallCallback {

	public void beforeMarshal(Marshaller marshaller);
}
