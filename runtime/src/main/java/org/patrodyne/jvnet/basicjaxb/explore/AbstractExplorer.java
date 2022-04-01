package org.patrodyne.jvnet.basicjaxb.explore;

import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import static javax.swing.JSplitPane.VERTICAL_SPLIT;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.WriterOutputStream;

/**
 * An abstract Swing JFrame to support exploration of HiSrc libraries.
 * This class provides JPanes to:
 * 
 * 1) Display HTML documentation.
 * 2) Organize output streams.
 * 
 * @author Rick O'Sullivan
 */
@SuppressWarnings("serial")
abstract public class AbstractExplorer extends JFrame
{
	protected static int CONFIG_WINDOW_WIDTH = 960;
	protected static int CONFIG_WINDOW_HEIGHT = 512;
	protected static int CONFIG_CONSOLE_FONT_POINTS = 14;
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
		errorln(ex.getClass().getSimpleName()+": "+ex.getMessage());
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
	
	private void createLayout(String htmlName)
	{
		JToolBar toolBar = createToolBar();
		JPanel contentPanel = createContentPanel(htmlName);
		add(toolBar, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JToolBar createToolBar()
	{
		JButton clearButton = createImageButton("/actions/edit-clear.png");
		clearButton.setToolTipText("Clear output panels.");
		clearButton.addActionListener((ae) -> {
			getConsoleWriter().clear();
			getErrorWriter().clear();
		});
		
		JButton zoomInButton = createImageButton("/actions/zoom-in-3.png");
		zoomInButton.setToolTipText("Larger text size.");
		zoomInButton.addActionListener((ae) -> {
			getConsoleWriter().largerText();
			getErrorWriter().largerText();
		});
		
		JButton zoomOutButton = createImageButton("/actions/zoom-out-3.png");
		zoomOutButton.setToolTipText("Smaller text size.");
		zoomOutButton.addActionListener((ae) -> {
			getConsoleWriter().smallerText();
			getErrorWriter().smallerText();
		});
		
		JToolBar toolBar = new JToolBar();
		toolBar.add(clearButton);
		toolBar.add(zoomInButton);
		toolBar.add(zoomOutButton);
		
		return toolBar;
	}
	
	private JButton createImageButton(String path)
	{
		return new JButton(new ImageIcon(AbstractExplorer.class.getResource(OILPATH+path)));
	}
	
	private JPanel createContentPanel(String htmlName)
	{
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());

		Border consoleBorder = BorderFactory.createRaisedBevelBorder();
		Border consoleTitleBorder = BorderFactory.createTitledBorder(consoleBorder, "Explore");
		contentPanel.setBorder(consoleTitleBorder);

		setConsoleWriter(new ConsoleWriter(CONFIG_CONSOLE_FONT_POINTS));
		setPrintStream(new PrintStream(new WriterOutputStream(getConsoleWriter(), CONFIG_CHARSET)));
		JScrollPane consolePane = new JScrollPane(getConsoleWriter().getTextArea());

		setErrorWriter(new ConsoleWriter(CONFIG_CONSOLE_FONT_POINTS));
		setErrorStream(new PrintStream(new WriterOutputStream(getErrorWriter(), CONFIG_CHARSET)));
		JScrollPane errorPane = new JScrollPane(getErrorWriter().getTextArea());

		JSplitPane streamPane = new JSplitPane(VERTICAL_SPLIT, consolePane, errorPane);
		streamPane.setResizeWeight(CONFIG_STREAM_PANE_WEIGHT);
		streamPane.setOneTouchExpandable(true);
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
		setSize(CONFIG_WINDOW_WIDTH, CONFIG_WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		getLessonPane().setDividerLocation(CONFIG_EXPLORE_PANE_WEIGHT);
		getStreamPane().setDividerLocation(CONFIG_STREAM_PANE_WEIGHT);
	}
}
