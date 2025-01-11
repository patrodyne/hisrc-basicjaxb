package org.swixml.jsr.widgets;

public class CardNodeInfo
{
	private String nodeName;
	public String getNodeName() { return nodeName; }
	public void setNodeName(String nodeName) { this.nodeName = nodeName; }

	private Class<?> cardClass;
	public Class<?> getCardClass() { return cardClass; }
	public void setCardClass(Class<?> cardClass) { this.cardClass = cardClass; }
	
	public String getCardName()
	{
		return getCardClass().getName();
	}
	
	public CardNodeInfo(String nodeName, Class<?> cardClass)
	{
		setNodeName(nodeName);
		setCardClass(cardClass);
	}
	
	@Override
	public String toString()
	{
		return getNodeName();
	}
}

