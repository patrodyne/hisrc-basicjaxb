package org.swixml.processor;

import java.awt.LayoutManager;

import org.swixml.Parser;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class NopTagProcessor implements TagProcessor
{
	public static final TagProcessor instance = new NopTagProcessor();

	@Override
	public boolean process(Parser p, Object obj, org.w3c.dom.Element child, LayoutManager layoutMgr)
		throws Exception
	{
		return true;
	}
}
