package org.example.wildcard.bio;

import static org.example.wildcard.bio.Context.mainMarshalWrap;
import static org.example.wildcard.bio.Context.mainUnmarshalByType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class BioXmlAdapter extends XmlAdapter<Element, Object>
{
	// Represents the logger for this class.
	private static Logger logger = LoggerFactory.getLogger(BioXmlAdapter.class);
	public static Logger getLogger() { return logger; }

	@Override
	public Object unmarshal(Element element) throws Exception
	{
		getLogger().trace("unmarshal: {}", element);
		return mainUnmarshalByType(element);
	}

	@Override
	public Element marshal(Object value) throws Exception
	{
		getLogger().trace("marshal: {}", value);
		return mainMarshalWrap(value);
	}
}
