package org.jvnet.basicjaxb.locator.util;

import org.xml.sax.Locator;

public class LocatorBean implements Locator
{
	private String publicId;
	@Override
	public String getPublicId() { return publicId; }
	public void setPublicId(String publicId) { this.publicId = publicId; }
	
	private String systemId;
	@Override
	public String getSystemId() { return systemId; }
	public void setSystemId(String systemId) { this.systemId = systemId; }

	private int lineNumber;
	@Override
	public int getLineNumber() { return lineNumber; }
	public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

	private int columnNumber;
	@Override
	public int getColumnNumber() { return columnNumber; }
	public void setColumnNumber(int columnNumber) { this.columnNumber = columnNumber; }
	
	/**
	 * Construct with all values.
	 * 
	 * @param publicId The DTD/XML public identifier.
	 * @param systemId The DTD/XML system identifier.
	 * @param lineNumber The source line number.
	 * @param columnNumber The source column number.
	 */
	public LocatorBean(String publicId, String systemId, int lineNumber, int columnNumber)
	{
		setPublicId(publicId);
		setSystemId(systemId);
		setLineNumber(lineNumber);
		setColumnNumber(columnNumber);
	}
	
	/**
	 * Construct with a SAX event with a document {@link Locator}.
	 * @param loc A SAX event with a document {@link Locator}.
	 */
	public LocatorBean(Locator loc)
	{
		this(loc.getPublicId(), loc.getSystemId(), loc.getLineNumber(), loc.getColumnNumber());
	}
	
	@Override
	public String toString()
	{
		return LocatorUtils.getLocation(this);
	}
}
