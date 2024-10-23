package org.swixml.legacy.product;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product
	implements Serializable
{
	private static final long serialVersionUID = 20241001L;

	private String partNum;
	public String getPartNum() { return partNum; }
	public void setPartNum(String value) { this.partNum = value; }
	
	private String picture;
	public String getPicture() { return picture; }
	public void setPicture(String value) { this.picture = value; }

	private String description;
	public String getDescription() { return description; }
	public void setDescription(String value) { this.description = value; }

	private BigDecimal price;
	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal value) { this.price = value; }
	
	public Object get(int index)
	{
		Object value = null;
		switch ( index )
		{
			case 0: value = this.getPartNum(); break;
			case 1: value = this.getPicture(); break;
			case 2: value = this.getDescription(); break;
			case 3: value = this.getPrice(); break;
		}
		return value;
	}
	public void set(int index, Object value)
	{
		switch ( index )
		{
			case 0: this.setPartNum((String) value); break;
			case 1: this.setPicture((String) value); break;
			case 2: this.setDescription((String) value); break;
			case 3: this.setPrice((BigDecimal) value); break;
		}
	}

	public Product(String partNum, String value)
	{
		setPartNum(partNum.toString());
		String[] values = value.split("\\|");
		setPicture(values[0]);
		setDescription(values[1]);
		setPrice(new BigDecimal(values[2]));
	}
}
