package org.swixml.processor;

import java.awt.LayoutManager;

import org.swixml.Parser;
import org.w3c.dom.Element;

public class ThemeColorsTagProcessor implements TagProcessor
{
	@Override
	public boolean process(Parser p, Object obj, Element child, LayoutManager layoutMgr)
		throws Exception
	{
		return true;
	}
}
