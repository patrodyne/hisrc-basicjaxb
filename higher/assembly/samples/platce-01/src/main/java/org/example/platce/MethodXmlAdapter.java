package org.example.platce;

import static org.example.platce.Context.mainMarshalWrapAsMethod;
import static org.example.platce.Context.mainUnmarshalByName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class MethodXmlAdapter extends XmlAdapter<Element, Object>
{
	// Represents the logger for this class.
	private static Logger logger = LoggerFactory.getLogger(MethodXmlAdapter.class);
	public static Logger getLogger() { return logger; }

	@Override
	public Object unmarshal(Element element) throws Exception
	{
		getLogger().trace("unmarshal: {}", element);
		return mainUnmarshalByName(element);
	}

	@Override
	public Element marshal(Object value) throws Exception
	{
		getLogger().trace("marshal: {}", value);
		return mainMarshalWrapAsMethod(value);
	}
}
