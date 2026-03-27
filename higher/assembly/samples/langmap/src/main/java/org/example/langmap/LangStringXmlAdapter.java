package org.example.langmap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Defines methods for adapting a {@link Map} type to a {@link LangString} type or vice versa.
 */
public class LangStringXmlAdapter extends XmlAdapter<LangString, Map<String,String>>
{
	private static final ObjectFactory OF = new ObjectFactory();

	@Override
	public Map<String,String> unmarshal(LangString langString) throws Exception
	{
		Map<String, String> map = new LinkedHashMap<>();
		if ( langString != null )
			map.put(langString.getLang(), langString.getValue());
		return map;
	}

	@Override
	public LangString marshal(Map<String,String> map) throws Exception
	{
		LangString langString = OF.createLangString();
		if ( (map != null) )
		{
			Optional<Map.Entry<String, String>> entry = map.entrySet().stream().findFirst();
			if ( entry.isPresent())
			{
				langString
					.useLang(entry.get().getKey())
					.useValue(entry.get().getValue());
			}
		}
		return langString;
	}
}
