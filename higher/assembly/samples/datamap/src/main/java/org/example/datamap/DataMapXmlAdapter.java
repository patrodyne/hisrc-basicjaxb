package org.example.datamap;

import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Defines methods for adapting a {@link Map} type to a {@link DataMap} type or vice versa.
 */
public class DataMapXmlAdapter extends XmlAdapter<DataMap, Map<String,Object>>
{
	@Override
	public Map<String,Object> unmarshal(DataMap dataMap) throws Exception
	{
		Map<String, Object> map = new LinkedHashMap<>();
		for ( Entry entry : dataMap.getEntry() )
		{
			if ( entry.getValue() instanceof Element )
			{
				Element entryValue = (Element) entry.getValue();
				if ( entryValue != null )
					map.put(entry.getKey(), entryValue.getTextContent());
			}
			else
				map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	@Override
	public DataMap marshal(Map<String,Object> map) throws Exception
	{
		DataMap dataMap = new DataMap();
		if ( map != null )
		{
			for ( java.util.Map.Entry<String, Object> entry : map.entrySet() )
			{
				Entry data = new Entry();
				data.setKey(entry.getKey());

				if ( entry.getValue() instanceof Element )
				{
					Element entryValue = (Element) entry.getValue();
					if ( entryValue != null )
						data.setValue(entryValue.getTextContent());
				}
				else
					data.setValue(entry.getValue());

				dataMap.getEntry().add(data);
			}
		}
		return dataMap;
	}
}
