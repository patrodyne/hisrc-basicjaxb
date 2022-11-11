package org.jvnet.basicjaxb.xml.bind;

import jakarta.xml.bind.Marshaller;

public interface AfterMarshallCallback {

	public void afterMarshal(Marshaller marshaller);
}
