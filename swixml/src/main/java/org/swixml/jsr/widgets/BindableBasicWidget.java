package org.swixml.jsr.widgets;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public interface BindableBasicWidget extends BindableWidget
{
	String BINDWITH_PROPERTY = "org.swixml.jsr.widgets.bindWith";

	String getBindWith();
	void setBindWith(String bindWith);
}
