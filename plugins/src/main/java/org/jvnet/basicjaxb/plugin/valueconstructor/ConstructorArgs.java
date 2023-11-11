package org.jvnet.basicjaxb.plugin.valueconstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.outline.FieldOutline;

/**
 * ConstructorArgs for process field outlines.
 */
public class ConstructorArgs
{
	protected JDefinedClass definedClass;
	protected List<FieldOutline> superFieldList;
	protected List<FieldOutline> localFieldList;
	protected String javaDoc;

	/** Default no-arg constructor */
	public ConstructorArgs()
	{
		super();
	}

	/** Full value constructor: Arrays */
	public ConstructorArgs(JDefinedClass definedClass, FieldOutline[] superFields,
		FieldOutline[] localFields, String javaDoc)
	{
		this.definedClass = definedClass;
		this.superFieldList = Arrays.asList(superFields);
		this.localFieldList = Arrays.asList(localFields);
		this.javaDoc = javaDoc;
	}
	
	/** Full value constructor: Lists */
	public ConstructorArgs(JDefinedClass definedClass, List<FieldOutline> superFieldList,
		List<FieldOutline> localFieldList, String javaDoc)
	{
		this.definedClass = definedClass;
		this.superFieldList = superFieldList;
		this.localFieldList = localFieldList;
		this.javaDoc = javaDoc;
	}

	/**
	 * Gets the value of the definedClass property.
	 * 
	 * @return possible object is {@link JDefinedClass }
	 */
	public JDefinedClass getDefinedClass()
	{
		return definedClass;
	}

	/**
	 * Sets the value of the definedClass property.
	 * 
	 * @param value allowed object is {@link JDefinedClass }
	 */
	public void setDefinedClass(JDefinedClass value)
	{
		this.definedClass = value;
	}

	/**
	 * Gets the value of the superFieldList property.
	 * 
	 * @return The value of the superFieldList property.
	 */
	public List<FieldOutline> getSuperFieldList()
	{
		if (superFieldList == null)
			superFieldList = new ArrayList<>();
		return this.superFieldList;
	}

	/**
	 * Gets the value of the localFieldList property.
	 * 
	 * @return The value of the localFieldList property.
	 */
	public List<FieldOutline> getLocalFieldList()
	{
		if (localFieldList == null)
			localFieldList = new ArrayList<>();
		return this.localFieldList;
	}

	/**
	 * Gets the value of the javaDoc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getJavaDoc()
	{
		return javaDoc;
	}

	/**
	 * Sets the value of the javaDoc property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setJavaDoc(String value)
	{
		this.javaDoc = value;
	}

	@Override
	public int hashCode()
	{
		int currentHashCode = 1;
		{
			currentHashCode = (currentHashCode * 31);
			JDefinedClass theDefinedClass = this.getDefinedClass();
			if ( this.definedClass != null )
				currentHashCode += theDefinedClass.hashCode();
		}
		{
			currentHashCode = (currentHashCode * 31);
			List<FieldOutline> theSuperFieldList = ((this.superFieldList != null) && (!this.superFieldList.isEmpty()))
				? this .getSuperFieldList() : null;
			if ( (this.superFieldList != null) && (!this.superFieldList.isEmpty()) )
				currentHashCode += theSuperFieldList.hashCode();
		}
		{
			currentHashCode = (currentHashCode * 31);
			List<FieldOutline> theLocalFieldList = ((this.localFieldList != null) && (!this.localFieldList.isEmpty()))
				? this .getLocalFieldList() : null;
			if ( (this.localFieldList != null) && (!this.localFieldList.isEmpty()) )
				currentHashCode += theLocalFieldList.hashCode();
		}
		// Intentionally ignore javadoc
		return currentHashCode;
	}

	@Override
	public boolean equals(Object object)
	{
		if ( (object == null) || (this.getClass() != object.getClass()) )
			return false;
		
		if (this == object)
			return true;
		
		final ConstructorArgs that = (ConstructorArgs) object;
		{
			JDefinedClass lhsDefinedClass = this.getDefinedClass();
			JDefinedClass rhsDefinedClass = that.getDefinedClass();
			if ( this.definedClass != null )
			{
				if ( that.definedClass != null )
				{
					if ( !lhsDefinedClass.equals(rhsDefinedClass) )
						return false;
				}
				else
					return false;
			}
			else
			{
				if ( that.definedClass != null )
					return false;
			}
		}
		{
			List<FieldOutline> lhsSuperFieldList = ((this.superFieldList != null) && (!this.superFieldList.isEmpty()))
				? this .getSuperFieldList() : null;
			List<FieldOutline> rhsSuperFieldList = ((that.superFieldList != null) && (!that.superFieldList.isEmpty()))
				? that.getSuperFieldList() : null;
			if ( (this.superFieldList != null) && (!this.superFieldList.isEmpty()) )
			{
				if ( (that.superFieldList != null) && (!that.superFieldList.isEmpty()) )
				{
					if ( !lhsSuperFieldList.equals(rhsSuperFieldList) )
						return false;
				}
				else
					return false;
			}
			else
			{
				if ( (that.superFieldList != null) && (!that.superFieldList.isEmpty()) )
					return false;
			}
		}
		{
			List<FieldOutline> lhsLocalFieldList = ((this.localFieldList != null) && (!this.localFieldList.isEmpty()))
				? this.getLocalFieldList() : null;
			List<FieldOutline> rhsLocalFieldList = ((that.localFieldList != null) && (!that.localFieldList.isEmpty()))
				? that.getLocalFieldList() : null;
			if ( (this.localFieldList != null) && (!this.localFieldList.isEmpty()) )
			{
				if ( (that.localFieldList != null) && (!that.localFieldList.isEmpty()) )
				{
					if ( !lhsLocalFieldList.equals(rhsLocalFieldList) )
						return false;
				}
				else
					return false;
			}
			else
			{
				if ( (that.localFieldList != null) && (!that.localFieldList.isEmpty()) )
					return false;
			}
		}
		// Intentionally ignore javadoc
		return true;
	}
}
