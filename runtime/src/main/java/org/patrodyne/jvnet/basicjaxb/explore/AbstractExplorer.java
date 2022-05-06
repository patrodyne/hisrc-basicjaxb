package org.patrodyne.jvnet.basicjaxb.explore;

import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import static javax.swing.SwingConstants.VERTICAL;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.event.HyperlinkEvent;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.WriterOutputStream;

/**
 * An abstract Swing JFrame to support exploration of HiSrc libraries.
 * This class provides JPanes to:
 * 
 * <ol>
 *   <li>Display HTML documentation.</li>
 *   <li>Organize output streams.</li>
 * </ol>
 * 
 * @author Rick O'Sullivan
 */
@SuppressWarnings("serial")
abstract public class AbstractExplorer extends JFrame
{
	protected static String CONSOLE_FONT_POINTS = "ConsoleFontPoints";
	protected static String DEFAULT_CONSOLE_FONT_POINTS = "14";
	protected static String WINDOW_LEFT="WindowLeft";
	protected static String WINDOW_TOP="WindowTop";
	protected static String WINDOW_WIDTH="WindowWidth";
	protected static String WINDOW_HEIGHT="WindowHeight";
	protected static String DEFAULT_WINDOW_LEFT = "100";
	protected static String DEFAULT_WINDOW_TOP = "100";
	protected static String DEFAULT_WINDOW_WIDTH = "960";
	protected static String DEFAULT_WINDOW_HEIGHT = "512";
	protected static double CONFIG_EXPLORE_PANE_WEIGHT = 0.40;
	protected static double CONFIG_STREAM_PANE_WEIGHT = 0.75;
	protected static Charset CONFIG_CHARSET = Charset.defaultCharset();
	protected static final String OILPATH = "open-icon-library/24x24";
	
	private JSplitPane streamPane;
	public JSplitPane getStreamPane() { return streamPane; }
	public void setStreamPane(JSplitPane streamPane) { this.streamPane = streamPane; }

	private JSplitPane lessonPane;
	public JSplitPane getLessonPane() { return lessonPane; }
	public void setLessonPane(JSplitPane lessonPane) { this.lessonPane = lessonPane; }
	
	private JToolBar toolBar;
	public JToolBar getToolBar() { return toolBar; }
	public void setToolBar(JToolBar toolBar) { this.toolBar = toolBar; }
	
	public HtmlPane getHtmlPane()
	{
		JViewport htmlViewPort = ((JScrollPane) getLessonPane().getLeftComponent()).getViewport();
		return (HtmlPane) htmlViewPort.getView();
	}
	
	protected static void printStackTrace(Throwable ex)
	{
		ex.printStackTrace();
	}

	protected static final class ErrorHandler implements Thread.UncaughtExceptionHandler
	{
		@Override
		public void uncaughtException(Thread t, Throwable ex)
		{
			printStackTrace(ex);
		}
	}
	
	private ConsoleWriter consoleWriter;
	public ConsoleWriter getConsoleWriter()
	{
		return consoleWriter;
	}
	public void setConsoleWriter(ConsoleWriter consoleWriter)
	{
		this.consoleWriter = consoleWriter;
	}

	private ConsoleWriter errorWriter;
	public ConsoleWriter getErrorWriter()
	{
		return errorWriter;
	}
	public void setErrorWriter(ConsoleWriter errorWriter)
	{
		this.errorWriter = errorWriter;
	}

	private PrintStream printStream = System.out;
	public PrintStream getPrintStream()
	{
		return printStream;
	}
	public void setPrintStream(PrintStream printStream)
	{
		this.printStream = printStream;
		System.setOut(printStream);
	}

	private PrintStream errorStream = System.err;
	public PrintStream getErrorStream()
	{
		return errorStream;
	}
	public void setErrorStream(PrintStream errorStream)
	{
		this.errorStream = errorStream;
		System.setErr(errorStream);
	}

	public void println()
	{
		println("");
	}
	public void println(Object obj)
	{
		if ( obj != null )
		{
			if ( getConsoleWriter() != null )
			{
				try
				{
					getConsoleWriter().write(obj.toString()+"\n");
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
			else
				getPrintStream().println(obj.toString());
			getPrintStream().flush();
		}
	}

	public void println(Object[] objs)
	{
		if ( objs != null )
		{
			for ( Object obj : objs )
				println(obj);
		}
	}

	public void errorln(Object obj)
	{
		if ( obj != null )
		{
			if ( getErrorWriter() != null )
			{
				try
				{
					getErrorWriter().write(obj.toString()+"\n");
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
			else
				getErrorStream().println(obj.toString());
			getErrorStream().flush();
		}
	}

	public void errorln(Exception ex)
	{
		if ( ex != null )
		{
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			errorln(sw.toString());
		}
	}
	
	public String getResourceAsString(Class<?> clazz, String resourceName)
	{
		StringBuilder html = new StringBuilder();
		try ( InputStream htmlStream = clazz.getResourceAsStream(resourceName) )
		{
			if (htmlStream != null)
				html.append(IOUtils.toString(htmlStream, StandardCharsets.UTF_8));
			else
				errorln("ERROR: Resource not found, "+resourceName+".");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return html.toString();
	}
	
	private Preferences getPreferences()
	{
		return Preferences.userNodeForPackage(getClass());
	}
	
	private void createLayout(String htmlName)
	{
		setToolBar(createToolBar());
		JPanel contentPanel = createContentPanel(htmlName);
		add(getToolBar(), BorderLayout.WEST);
		add(contentPanel, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				Preferences prefs = getPreferences();
				prefs.put(WINDOW_LEFT, round(getLocation().getX()));
				prefs.put(WINDOW_TOP, round(getLocation().getY()));
				prefs.put(WINDOW_WIDTH, round(getSize().getWidth()));
				prefs.put(WINDOW_HEIGHT, round(getSize().getHeight()));
				prefs.put(CONSOLE_FONT_POINTS, points(getConsoleWriter().getTextArea()));
			}

			private String round(double value)
			{
				return String.format("%.0f", value);
			}
			
			private String points(JTextArea textArea)
			{
				return Integer.toString(textArea.getFont().getSize());
			}
		});
		addHyperlinkDispatcher();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void addHyperlinkDispatcher()
	{
		getHtmlPane().addHyperlinkListener((HyperlinkEvent event) -> {
			if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				if ( event.getDescription().startsWith("!") ) {
					dispatchHyperLink(event.getDescription().substring(1));
				}
			}
		});
	}

	/**
	 * Dispatch hyperlink to local method invocation.
	 * Sub-class should override this method when local links are used.
	 * 
	 * The markdown for hyperlinks is declared like this:
	 * 
	 *   [description](!hyperLink)
	 */
	public void dispatchHyperLink(String hyperLink)
	{
		// Dispatch hyperlink to local method invocation.
	}
	
	private JToolBar createToolBar()
	{
		JButton clearButton = createImageButton(AbstractExplorer.class, OILPATH+"/actions/edit-clear.png");
		clearButton.setToolTipText("Clear output panels.");
		clearButton.addActionListener((ae) -> {
			getConsoleWriter().clear();
			getErrorWriter().clear();
		});
		
		JButton zoomInButton = createImageButton(AbstractExplorer.class, OILPATH+"/actions/zoom-in-3.png");
		zoomInButton.setToolTipText("Larger text size.");
		zoomInButton.addActionListener((ae) -> {
			getConsoleWriter().largerText();
			getErrorWriter().largerText();
		});
		
		JButton zoomOutButton = createImageButton(AbstractExplorer.class, OILPATH+"/actions/zoom-out-3.png");
		zoomOutButton.setToolTipText("Smaller text size.");
		zoomOutButton.addActionListener((ae) -> {
			getConsoleWriter().smallerText();
			getErrorWriter().smallerText();
		});
		
		JToolBar toolBar = new JToolBar(VERTICAL);
		toolBar.add(clearButton);
		toolBar.add(zoomInButton);
		toolBar.add(zoomOutButton);
		
		return toolBar;
	}
	
	protected ImageIcon createImageIcon(Class<?> clazz, String path)
	{
		return new ImageIcon(clazz.getResource(path));
	}
	
	protected JButton createImageButton(Class<?> clazz, String path)
	{
		return new JButton(createImageIcon(clazz, path));
	}
	
	protected JToggleButton createImageToggleButton(Class<?> clazz, String iconPath1, String iconPath2)
	{
		JToggleButton toggleButton = new JToggleButton(createImageIcon(clazz, iconPath1));
		toggleButton.setSelectedIcon(createImageIcon(clazz, iconPath2));
		return toggleButton;
	}
	
	private JPanel createContentPanel(String htmlName)
	{
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());

		Integer fontPoints = new Integer(getPreferences().get(CONSOLE_FONT_POINTS, DEFAULT_CONSOLE_FONT_POINTS));
		
		setConsoleWriter(new ConsoleWriter(fontPoints));
		setPrintStream(new PrintStream(new WriterOutputStream(getConsoleWriter(), CONFIG_CHARSET)));
		JScrollPane consolePane = new JScrollPane(getConsoleWriter().getTextArea());

		setErrorWriter(new ConsoleWriter(fontPoints));
		setErrorStream(new PrintStream(new WriterOutputStream(getErrorWriter(), CONFIG_CHARSET)));
		JScrollPane errorPane = new JScrollPane(getErrorWriter().getTextArea());

		JSplitPane streamPane = new JSplitPane(VERTICAL_SPLIT, consolePane, errorPane);
		streamPane.setOneTouchExpandable(true);
		streamPane.setResizeWeight(CONFIG_STREAM_PANE_WEIGHT);
		setStreamPane(streamPane);

		String html = getResourceAsString(getClass(), htmlName);
		JScrollPane htmlPane = new JScrollPane(new HtmlPane(html));
		JSplitPane lessonPane = new JSplitPane(HORIZONTAL_SPLIT, htmlPane, streamPane);
		lessonPane.setOneTouchExpandable(true);
		lessonPane.setResizeWeight(CONFIG_EXPLORE_PANE_WEIGHT);
		setLessonPane(lessonPane);

		contentPanel.add(lessonPane);
		
		return contentPanel;
	}
	
	/**
	 * Construct application
	 */
	public AbstractExplorer(String htmlName)
	{
		super();
		Thread.setDefaultUncaughtExceptionHandler(new ErrorHandler());
		createLayout(htmlName);
		pack();
		Preferences prefs = getPreferences();
		setSize
		(
			new Integer(prefs.get(WINDOW_WIDTH, DEFAULT_WINDOW_WIDTH)),
			new Integer(prefs.get(WINDOW_HEIGHT, DEFAULT_WINDOW_HEIGHT))
		);
		setLocation
		(
			new Integer(prefs.get(WINDOW_LEFT, DEFAULT_WINDOW_LEFT)),
			new Integer(prefs.get(WINDOW_TOP, DEFAULT_WINDOW_TOP))
		);
		setVisible(true);
		getLessonPane().setDividerLocation(CONFIG_EXPLORE_PANE_WEIGHT);
		getStreamPane().setDividerLocation(CONFIG_STREAM_PANE_WEIGHT);
	}
}
