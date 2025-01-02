package org.jvnet.basicjaxb.lang;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An extension of {@link SimpleBeanInfo} with additional properties for
 * {@link DataDescriptor} and an array of {@link FieldDescriptor}.
 * 
 * <p>
 * This is a logging support class to make it easier for people to provide
 * BeanInfo classes.
 * </p>
 * 
 * <p>
 * It defaults to providing {@link SimpleBeanInfo} information, and can be
 * selectively overridden to provide more explicit information on chosen topics.
 * </p>
 */
public class DataBeanInfo extends SimpleBeanInfo
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	public Logger getLogger() { return logger; }
	
	/** Default constructor */
	public DataBeanInfo()
	{
		super();
	}
	
	/**
	 * Construct with an existing {@link BeanInfo}.
	 * 
	 * @param beanInfo Provides explicit information about the methods,
	 *                 properties, events, and other features of a Java bean.
	 */
	public DataBeanInfo(BeanInfo beanInfo)
	{
		setDelegateBeanInfo(beanInfo);
	}
	
	private BeanInfo delegateBeanInfo;
    public BeanInfo getDelegateBeanInfo()
	{
		return delegateBeanInfo;
	}
	public void setDelegateBeanInfo(BeanInfo delegateBeanInfo)
	{
		this.delegateBeanInfo = delegateBeanInfo;
	}

	/**
	 * Returns the bean descriptor.
	 * 
	 * @return  a {@link BeanDescriptor} object or {@code null}.
     */
    @Override
    public BeanDescriptor getBeanDescriptor()
    {
    	if ( getDelegateBeanInfo() != null )
    		return getDelegateBeanInfo().getBeanDescriptor();
    	else
    		return super.getBeanDescriptor();
    }

    /**
     * Returns descriptors for all properties of the bean.
	 * 
	 * @return  a {@link PropertyDescriptor} object or {@code null}.
     */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors()
    {
    	if ( getDelegateBeanInfo() != null )
    		return getDelegateBeanInfo().getPropertyDescriptors();
    	else
    		return super.getPropertyDescriptors();
    }

	private DataDescriptor dataDescriptor;
	public DataDescriptor getDataDescriptor()
	{
		if ( dataDescriptor == null )
			setDataDescriptor(toDataDescriptor(getBeanDescriptor()));
		return dataDescriptor;
	}
	public void setDataDescriptor(DataDescriptor dataDescriptor)
	{
		this.dataDescriptor = dataDescriptor;
	}
	
	private FieldDescriptor[] fieldDescriptors;
	public FieldDescriptor[] getFieldDescriptors()
	{
		if ( fieldDescriptors == null )
		{
			try
			{
				setFieldDescriptors(toFieldDescriptors(getPropertyDescriptors()));
			}
			catch (IntrospectionException ie)
			{
				setFieldDescriptors(new FieldDescriptor[0]);
				logger.error("getFieldDescriptors: ", ie);
			}
		}
		return fieldDescriptors;
	}
	public void setFieldDescriptors(FieldDescriptor[] fieldDescriptors)
	{
		this.fieldDescriptors = fieldDescriptors;
	}
	
	private Object data;
	public Object getData()
	{
		return data;
	}
	public void setData(Object data)
	{
		this.data = data;
	}

	/*
	 * Promote a {@link BeanDescriptor} to a {@link DataDescriptor}.
	 * 
	 * @param bd The bean descriptor to promote.
	 * 
	 * @return A {@link BeabDescriptor} instance of a {@link BeanDescriptor}.
	 * 
	 * @throws IntrospectionException When the bean class cannot be introspected.
	 */
	private DataDescriptor toDataDescriptor(BeanDescriptor bd)
	{
		DataDescriptor dataDescriptor;
		if ( bd instanceof DataDescriptor )
			dataDescriptor = DataDescriptor.promote(bd);
		else
			dataDescriptor = new DataDescriptor(bd);
		return dataDescriptor;
	}
	
	/*
	 * Promote an array of {@link PropertyDescriptor} to an array of
	 * {@link FieldDescriptor}.
	 * 
	 * @param <E> The generic type of the bean class.
	 * @param pdArray The array of property descriptors to promote.
	 * 
	 * @return An index-ordered array of field descriptors.
	 * 
	 * @throws IntrospectionException When the bean class cannot be introspected.
	 */
	private <E> FieldDescriptor[] toFieldDescriptors(PropertyDescriptor[] pdArray)
		throws IntrospectionException
	{
		FieldDescriptor[] fdArray = new FieldDescriptor[pdArray.length];
		for ( int index=0; index < pdArray.length; ++index )
		{
			PropertyDescriptor pd = pdArray[index];
			if ( pd instanceof FieldDescriptor )
				fdArray[index] = FieldDescriptor.promote(pd);
			else
				fdArray[index] = new FieldDescriptor(pd);
		}
		// Re-index and sort an array of introspected {@link FieldDescriptor}s.
		return reindex(fdArray);
	}
	
	/*
	 * Re-index and sort an array of introspected {@link FieldDescriptor}s.
	 * 
	 * @param bfdArray1 An array of BeanInfo introspected field descriptors.
	 * 
	 * @return An index-ordered array of {@link FieldDescriptor}s.
	 */
	private FieldDescriptor[] reindex(FieldDescriptor[] bfdArray1)
	{
		// Order FieldDescriptor(s) by existing column index, if any;
		// otherwise, define the index alphabetically by display name.
		TreeMap<Integer, FieldDescriptor> bfdMap1 = new TreeMap<>();
		TreeMap<String, FieldDescriptor> bfdMap2 = new TreeMap<>();
		for ( FieldDescriptor bfd : bfdArray1 )
		{
			if ( bfd.getIndex() != null  )
				bfdMap1.put(bfd.getIndex(), bfd);
			else
				bfdMap2.put(bfd.getDisplayName(), bfd);
		}
		
		// Index cached FieldDescriptor(s) alphabetically by display name.
		if ( !bfdMap2.isEmpty() )
		{
			Integer index = bfdMap1.isEmpty() ? 0 : bfdMap1.lastKey();
			for ( FieldDescriptor bfd2 : bfdMap2.values() )
				bfdMap1.put(++index, bfd2);
		}
		
		// Re-index, in sequence without gaps, zero-based.
		// Note: The bfdMap1's value iterator returns the values in
		//       ascending order of the corresponding keys.
		FieldDescriptor[] bdfArray2 = new FieldDescriptor[bfdMap1.size()];
		int index = 0;
		for ( FieldDescriptor bfd1 : bfdMap1.values() )
		{
			bfd1.setIndex(index);
			bdfArray2[index++] = bfd1;
		}
		return bdfArray2;
	}
}
