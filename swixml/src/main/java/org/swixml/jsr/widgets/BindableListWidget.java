package org.swixml.jsr.widgets;

import java.util.List;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public interface BindableListWidget extends BindableWidget
{
	List<?> getBindList();

	void setBindList(java.util.List<?> beanList);
}
