package org.swixml.processor;

import java.awt.LayoutManager;

import org.swixml.Parser;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public interface TagProcessor
{
	boolean process(Parser p, Object obj, org.w3c.dom.Element child, LayoutManager layoutMgr)
		throws Exception;
}
