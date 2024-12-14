package org.swixml.legacy.product;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.util.ArrayList;
import java.util.List;

import org.jvnet.basicjaxb.lang.FieldDescriptor;

public class ProductBeanInfo extends SimpleBeanInfo
{
	@Override
	public PropertyDescriptor[] getPropertyDescriptors()
	{
		try
		{
			return getFieldDescriptors2();
		}
		catch (IntrospectionException ie)
		{
			ie.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	private FieldDescriptor[] getFieldDescriptors1()
		throws IntrospectionException
	{
		FieldDescriptor[] fds = null;
		fds = new FieldDescriptor[]
		{
			 new FieldDescriptor("partNum", Product.class),
			 new FieldDescriptor("picture", Product.class),
			 new FieldDescriptor("description", Product.class),
			 new FieldDescriptor("price", Product.class)
		};

		if ( fds != null )
		{
			for ( FieldDescriptor fd : fds )
			{
				switch ( fd.getName() )
				{
					case "partNum":
						fd.setMaxWidth(14);
						break;
					case "picture":
						fd.setMaxWidth(3);
						break;
					case "description":
						fd.setMaxWidth(20);
						break;
					case "price":
						fd.setMaxWidth(10);
						break;
				}
			}
		}
		return fds;
	}
	
	private FieldDescriptor[] getFieldDescriptors2()
		throws IntrospectionException
	{
		List<FieldDescriptor> fdList = new ArrayList<>();
		
		FieldDescriptor fd = null;
		
		fd = new FieldDescriptor("partNum", Product.class);
		fd.setIndex(0);
		fd.setMaxWidth(14);
		fdList.add(fd);
		
		fd = new FieldDescriptor("picture", Product.class);
		fd.setIndex(1);
		fd.setMaxWidth(3);
		fdList.add(fd);
		
		fd = new FieldDescriptor("description", Product.class);
		fd.setIndex(2);
		fd.setMaxWidth(20);
		fdList.add(fd);
		
		fd = new FieldDescriptor("price", Product.class);
		fd.setIndex(3);
		fd.setMaxWidth(10);
		fdList.add(fd);
		
		return fdList.toArray(new FieldDescriptor[fdList.size()]);
	}

}
