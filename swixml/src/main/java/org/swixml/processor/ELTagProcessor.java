package org.swixml.processor;

import static org.swixml.el.ELUtility.eval;

import java.awt.LayoutManager;

import org.swixml.LogAware;
import org.swixml.Parser;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 */
public class ELTagProcessor implements TagProcessor, LogAware
{
	public static final TagProcessor instance = new ELTagProcessor();

	@Override
	public boolean process(Parser parser, Object obj, org.w3c.dom.Element child, LayoutManager layoutMgr)
		throws Exception
	{
		boolean processed = false;
		
		if ( Parser.TAG_SCRIPT.equalsIgnoreCase(child.getLocalName()) )
		{
			logger.debug(String.format("EL processor [%s]", child.getTextContent()));
			eval(parser.getSwingEngine().getELProcessor(), child.getTextContent());
			processed = true;
		}
		
		return processed;
	}
}
