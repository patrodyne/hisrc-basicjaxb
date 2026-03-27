package org.example.langmap;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Defines methods for adapting a {@link Map} type to a {@link EntryLangString} type or vice versa.
 */
public class LangMapXmlAdapter extends XmlAdapter<EntryLangString, Map<String,String>>
{
	private static final ObjectFactory OF = new ObjectFactory();

	@Override
	public Map<String,String> unmarshal(EntryLangString langMap) throws Exception
	{
		Map<String, String> map = new LinkedHashMap<>();
		for ( LangString entry : langMap.getEntry() )
		{
			if ( entry != null )
				map.put(entry.getLang(), entry.getValue());
		}
		return map;
	}

	@Override
	public EntryLangString marshal(Map<String,String> map) throws Exception
	{
		EntryLangString langMap = OF.createEntryLangString();
		if ( map != null )
		{
			for ( java.util.Map.Entry<String, String> entry : map.entrySet() )
			{
				LangString langString = OF.createLangString()
					.useLang(entry.getKey())
					.useValue(entry.getValue());
				langMap.getEntry().add(langString);
			}
		}
		return langMap;
	}
}
