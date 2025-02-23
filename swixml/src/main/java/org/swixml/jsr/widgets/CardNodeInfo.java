package org.swixml.jsr.widgets;

public class CardNodeInfo
{
	private String nodeName;
	public String getNodeName() { return nodeName; }
	public void setNodeName(String nodeName) { this.nodeName = nodeName; }

	private Class<?> cardClass;
	public Class<?> getCardClass() { return cardClass; }
	public void setCardClass(Class<?> cardClass) { this.cardClass = cardClass; }
	
	private String cardName;
	public String getCardName()
	{
		if ( (cardName == null) && (getCardClass() != null) && (getCardClass().getName() != null) )
			setCardName(getCardClass().getName());
		return cardName;
	}
	public void setCardName(String cardName)
	{
		this.cardName = cardName;
	}
	
	public CardNodeInfo(String nodeName, String cardName)
	{
		setNodeName(nodeName);
		setCardName(cardName);
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

