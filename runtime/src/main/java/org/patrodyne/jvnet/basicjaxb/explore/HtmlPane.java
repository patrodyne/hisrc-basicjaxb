package org.patrodyne.jvnet.basicjaxb.explore;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * A read-only HTML component with custom styles.
 * 
 * @author Rick O'Sullivan
 */
@SuppressWarnings("serial")
public class HtmlPane extends JEditorPane
{
	public HtmlPane(String html)
	{
		super();
		setEditable(false);
		setContentType("text/html");
		setEditorKit(createEditorKit());
		addHyperlinkListener((HyperlinkEvent event) -> {
			if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				if ( event.getURL() != null )
				{
					try { Desktop.getDesktop().browse(URI.create(event.getURL().toString())); }
					catch (IOException ex) { ex.printStackTrace(); }
				}
//				else if ( event.getDescription().startsWith("!") )
//				{
//					System.out.println("BANG");
//				}
			}
		});
		setText(html);
		setCaretPosition(0);
	}

	private HTMLEditorKit createEditorKit()
	{
		HTMLEditorKit kit = new HTMLEditorKit();
		StyleSheet styleSheet = kit.getStyleSheet();
		styleSheet.addRule("body { font-size: medium; font-family: sans-serif; font-weight: lighter; margin-left: 0; margin-right: 0; color: black; }");
		styleSheet.addRule("h1 { font-size: x-large; font-weight: bold; margin-top: 15; margin-bottom: 10; }");
		styleSheet.addRule("h2 { font-size: large; font-weight: bold; margin-top: 15; margin-bottom: 10; }");
		styleSheet.addRule("h3 { font-size: medium; font-weight: bold; margin-top: 15; margin-bottom: 10; }");
		styleSheet.addRule("h4 { font-size: small; font-weight: bold; margin-top: 15; margin-bottom: 10; }");
		styleSheet.addRule("h5 { font-size: x-small; font-weight: bold; margin-top: 15; margin-bottom: 10; }");
		styleSheet.addRule("h6 { font-size: xx-small; font-weight: bold; margin-top: 15; margin-bottom: 10; }");
		styleSheet.addRule("big { font-size: xx-large; }");
		styleSheet.addRule("small { font-size: small; }");
		styleSheet.addRule("samp { font-size: medium; font-family: Monospaced; }");
		styleSheet.addRule("pre { margin-top: 15; }");
		styleSheet.addRule("code { font-size: medium; font-family: Monospaced; font-weight: bold; }");
		styleSheet.addRule("pre code { font-weight: normal;  }");
		styleSheet.addRule("kbd { font-size: medium; font-family: Monospaced; }");
		styleSheet.addRule("dt { margin-top: 0; margin-bottom: 0; font-weight: bold; }");
		styleSheet.addRule("table { border-width: thin; border-style: solid; border-spacing: 0; overflow: auto; font-family: monospace; font-size: medium; }");
		styleSheet.addRule("th, td { border-width: thin; border-style: solid; padding: 3; }");
		styleSheet.addRule("th { text-align: center; font-weight: bold; }");
		return kit;
	}
}
