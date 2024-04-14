package org.example.document;

import static org.jvnet.basicjaxb.dom.DOMUtils.logNode;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DocumentDataMapXmlAdapter extends XmlAdapter<DvDataMap, Map<String,Object>>
{
	// Represents the logger for this class.
	private static Logger logger = LoggerFactory.getLogger(DocumentDataMapXmlAdapter.class);
	public static Logger getLogger() { return logger; }

	@Override
	public Map<String,Object> unmarshal(DvDataMap dataMap) throws Exception
	{
		getLogger().trace("unmarshal: {}", dataMap);
		Map<String, Object> map = new LinkedHashMap<>();
		for ( Entry entry : dataMap.getEntry() )
		{
			if ( entry.getValue() instanceof Element )
			{
				Element entryValue = (Element) entry.getValue();
				if ( entryValue != null )
				{
					map.put(entry.getKey(), entryValue.getTextContent());
					logNode(getLogger(), "Unmarshall DvDataMap Entry", (Node) entryValue);
				}
			}
			else
				map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	@Override
	public DvDataMap marshal(Map<String,Object> map) throws Exception
	{
		getLogger().trace("marshal: {}", map);
		DvDataMap dataMap = new DvDataMap();
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
					{
						data.setValue(entryValue.getTextContent());
						logNode(getLogger(), "Marshall Map Entry", (Node) entryValue);
					}
				}
				else
					data.setValue(entry.getValue());
				
				dataMap.getEntry().add(data);
			}			
		}
		return dataMap;
	}
}
