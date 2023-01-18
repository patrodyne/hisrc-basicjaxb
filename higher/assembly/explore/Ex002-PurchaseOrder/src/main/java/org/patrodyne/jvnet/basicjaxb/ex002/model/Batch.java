package org.patrodyne.jvnet.basicjaxb.ex002.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * An interface to represent a batch of entities including a batch timestamp,
 * list and size methods.
 */
public interface Batch
{
	public XMLGregorianCalendar getBatchTime();
	public void setBatchTime(XMLGregorianCalendar value);
	
	default public List<?> list()
	{
		List<?> list = new ArrayList<>();
		switch ( getClass().getSimpleName() )
		{
			case "AddressBatch": list = ((AddressBatch) this).getAddress(); break;
			case "CatalogueBatch": list = ((CatalogueBatch) this).getCatalogue(); break;
			case "PurchaseOrderBatch": list = ((PurchaseOrderBatch) this).getPurchaseOrder(); break;
		}
		return list;
	}
	
	default public int size()
	{
		return list().size();
	}
}
