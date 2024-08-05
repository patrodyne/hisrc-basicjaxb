package org.swixml.jsr.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.border.EtchedBorder;
import org.swixml.LogAware;

/**
 * Class to show toasters in multi-platform.
 *
 * <p>Java Toaster is a java utility class for your swing applications
 * that show an animate box coming from the bottom of your screen
 * with a notification message and/or an associated image
 * (like msn online/offline notifications).</p>
 *
 * <p>Toaster panel in windows system follow the taskbar; So if
 * the taskbar is into the bottom the panel coming from the bottom
 * and if the taskbar is on the top then the panel coming from the top.</p>
 *
 * <p>This is a simple example of utilization:</p>
 *
 * <pre>
 * import com.nitido.utils.toaster.*;
 * import javax.swing.*;
 *
 * public class ToasterTest
 * {
 *
 *  public static void main(String[] args)
 *  {
 *   // Initialize toaster manager...
 *   Toaster toasterManager = new Toaster();
 *
 *   // Show a simple toaster
 *   toasterManager.showToaster( new ImageIcon( "mylogo.gif" ), "A simple toaster with an image" );
 *  }
 * }
 * </pre>
 *
 * <p>
 * This java file is copyright by Daniele Piras ("danielepiras80", no email known) released under
 * the Apache Software License 2.0. It has been downloaded in December 2009 from the CVS web
 * interface of <a href="https://sourceforge.net/projects/jtoaster/">JToaster</a>.
 * The web interface to CVS is not available anymore on SourceForge.
 * </p>
 *
 * @author daniele piras
 *
 */
public class Toaster implements LogAware
{
	// Width of the toaster
	private int toasterWidth = 300;
	// Height of the toaster
	private int toasterHeight = 80;
	// Step for the toaster
	private int step = 20;
	// Step time
	private int stepTime = 20;
	// Show time
	private long displayTime = 3000L;
	// Current number of toaster...
	private int currentNumberOfToaster = 0;
	// Last opened toaster
	private int maxToaster = 0;
	// Max number of toasters for the screen
	private int maxToasterInSceen;
	// Font used to display message
	private Font font;
	// Color for border
	private Color borderColor;
	// Color for toaster
	private Color toasterColor;
	// Set message color
	private Color messageColor;
	// Set the margin
	int margin;
	boolean autoDismiss = true;
	// Flag that indicate if use alwaysOnTop or not.
	// method always on top start only SINCE JDK 5 !
	boolean useAlwaysOnTop = true;
	final ReentrantLock _lock = new ReentrantLock();
	final Condition _waitForDismiss;

	/**
	 * Constructor to initialized toaster component...
	 */
	public Toaster()
	{
		// Set default font...
		font = new Font("SansSerif", Font.BOLD, 12);
		// Border color
		borderColor = new Color(245, 153, 15);
		toasterColor = Color.WHITE;
		messageColor = Color.BLACK;
		useAlwaysOnTop = true;
		// Verify AlwaysOnTop Flag...
		try
		{
			JWindow.class.getMethod("setAlwaysOnTop", new Class[] { Boolean.class });
		}
		catch (Exception e)
		{
			useAlwaysOnTop = false;
		}
		_waitForDismiss = _lock.newCondition();
	}

	/**
	 * Signal and wait for dismiss.
	 */
	public final void dismiss()
	{
		_lock.lock();
		try
		{
			_waitForDismiss.signal();
		}
		finally
		{
			_lock.unlock();
		}
	}

	/**
	 * Class that represent a single toaster
	 * 
	 * @author daniele piras
	 * 
	 */
	class SingleToaster extends javax.swing.JWindow
	{
		private static final long serialVersionUID = 1L;
		// Label to store Icon
		private JLabel iconLabel = new JLabel();
		// Text area for the message
		private JTextArea message = new JTextArea();

		/***
		 * Simple constructor that initialized components...
		 */
		public SingleToaster()
		{
			initComponents();
		}

		/***
		 * Function to initialized components
		 */
		private void initComponents()
		{
			setSize(toasterWidth, toasterHeight);
			message.setFont(getToasterMessageFont());
			JPanel externalPanel = new JPanel(new BorderLayout(1, 1));
			JPanel innerPanel = new JPanel(new BorderLayout(getMargin(), getMargin()));
			externalPanel.setBackground(getBorderColor());
			innerPanel.setBackground(getToasterColor());
			message.setBackground(getToasterColor());
			message.setMargin(new Insets(2, 2, 2, 2));
			message.setLineWrap(true);
			message.setWrapStyleWord(true);
			EtchedBorder etchedBorder = (EtchedBorder) BorderFactory.createEtchedBorder();
			externalPanel.setBorder(etchedBorder);
			externalPanel.add(innerPanel);
			message.setForeground(getMessageColor());
			innerPanel.add(iconLabel, BorderLayout.WEST);
			innerPanel.add(message, BorderLayout.CENTER);
			getContentPane().add(externalPanel);
		}
	}

	/***
	 * Class that manage the animation
	 */
	public class AnimationThread extends Thread
	{
		javax.swing.JWindow toaster;

		public AnimationThread(javax.swing.JWindow toaster)
		{
			this.toaster = toaster;
		}

		/**
		 * Animate vertically the toaster. The toaster could be moved from
		 * bottom to upper or to upper to bottom
		 * 
		 * @param posX The X position
		 * @param fromY From this Y position
		 * @param toY To this Y position
		 * 
		 * @throws InterruptedException
		 */
		public void animateVertically(int posX, int fromY, int toY)
			throws InterruptedException
		{
			toaster.setLocation(posX, fromY);
			if ( toY < fromY )
			{
				for ( int i = fromY; i > toY; i -= step )
				{
					toaster.setLocation(posX, i);
					Thread.sleep(stepTime);
				}
			}
			else
			{
				for ( int i = fromY; i < toY; i += step )
				{
					toaster.setLocation(posX, i);
					Thread.sleep(stepTime);
				}
			}
			toaster.setLocation(posX, toY);
		}

		@Override
		public void run()
		{
			try
			{
				boolean animateFromBottom = true;
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				Rectangle screenRect = ge.getMaximumWindowBounds();
				int screenHeight = (int) screenRect.height;
				int startYPosition;
				int stopYPosition;
				if ( screenRect.y > 0 )
				{
					animateFromBottom = false; // Animate from top!
				}
				maxToasterInSceen = screenHeight / toasterHeight;
				int posx = (int) screenRect.width - toasterWidth - 1;
				toaster.setLocation(posx, screenHeight);
				toaster.setVisible(true);
				if ( useAlwaysOnTop )
				{
					toaster.setAlwaysOnTop(true);
				}
				if ( animateFromBottom )
				{
					startYPosition = screenHeight;
					stopYPosition = startYPosition - toasterHeight - 1;
					if ( currentNumberOfToaster > 0 )
					{
						stopYPosition = stopYPosition - (maxToaster % maxToasterInSceen * toasterHeight);
					}
					else
					{
						maxToaster = 0;
					}
				}
				else
				{
					startYPosition = screenRect.y - toasterHeight;
					stopYPosition = screenRect.y;
					if ( currentNumberOfToaster > 0 )
					{
						stopYPosition = stopYPosition + (maxToaster % maxToasterInSceen * toasterHeight);
					}
					else
					{
						maxToaster = 0;
					}
				}
				currentNumberOfToaster++;
				maxToaster++;
				animateVertically(posx, startYPosition, stopYPosition);
				if ( isAutoDismiss() )
				{
					if ( getDisplayTime() > 0 )
					{
						Thread.sleep(displayTime);
					}
				}
				else
				{
					_lock.lock();
					try
					{
						if ( getDisplayTime() > 0 )
						{
							if ( !_waitForDismiss.await(getDisplayTime(), TimeUnit.MILLISECONDS) )
							{
								logger.warn("waitForDismiss timeout.....");
							}
						}
						else
						{
							_waitForDismiss.await();
						}
					}
					finally
					{
						_lock.unlock();
					}
				}
				animateVertically(posx, stopYPosition, startYPosition);
				toaster.setVisible(false);
				toaster.dispose();
				currentNumberOfToaster--;
			}
			catch (Exception e)
			{
				logger.error("error rendering toaster", e);
			}
		}
	}

	/**
	 * Show a toaster with the specified message and the associated icon.
	 */
	public void showToaster(Icon icon, String msg)
	{
		SingleToaster singleToaster = new SingleToaster();
		if ( icon != null )
		{
			singleToaster.iconLabel.setIcon(icon);
		}
		singleToaster.message.setText(msg);
		animate(singleToaster);
	}

	/**
	 * Show a toaster with the specified message.
	 */
	public final void showToaster(String msg)
	{
		showToaster(null, msg);
	}

	/**
	 * 
	 * @param <T>
	 * @param toaster
	 * @return
	 */
	protected <T extends javax.swing.JWindow> AnimationThread animate(T toaster)
	{
		final AnimationThread anim = new AnimationThread(toaster);
		anim.start();
		return anim;
	}

	public boolean isAutoDismiss()
	{
		return autoDismiss;
	}

	public void setAutoDismiss(boolean autoDismiss)
	{
		this.autoDismiss = autoDismiss;
	}

	/**
	 * @return Returns the font
	 */
	public Font getToasterMessageFont()
	{
		return font;
	}

	/**
	 * Set the font for the message
	 */
	public Toaster setToasterMessageFont(Font f)
	{
		font = f;
		return this;
	}

	/**
	 * @return Returns the borderColor.
	 */
	public Color getBorderColor()
	{
		return borderColor;
	}

	/**
	 * @param borderColor The borderColor to set.
	 */
	public Toaster setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
		return this;
	}

	/**
	 * @return Returns the displayTime.
	 */
	public long getDisplayTime()
	{
		return displayTime;
	}

	/**
	 * @param displayTime The displayTime to set.
	 */
	public Toaster setDisplayTime(long displayTime)
	{
		this.displayTime = displayTime;
		return this;
	}

	/**
	 * @return Returns the margin.
	 */
	public int getMargin()
	{
		return margin;
	}

	/**
	 * @param margin The margin to set.
	 */
	public Toaster setMargin(int margin)
	{
		this.margin = margin;
		return this;
	}

	/**
	 * @return Returns the messageColor.
	 */
	public Color getMessageColor()
	{
		return messageColor;
	}

	/**
	 * @param messageColor The messageColor to set.
	 */
	public Toaster setMessageColor(Color messageColor)
	{
		this.messageColor = messageColor;
		return this;
	}

	/**
	 * @return Returns the step.
	 */
	public int getStep()
	{
		return step;
	}

	/**
	 * @param step The step to set.
	 */
	public Toaster setStep(int step)
	{
		this.step = step;
		return this;
	}

	/**
	 * @return Returns the stepTime in mills.
	 */
	public int getStepTime()
	{
		return stepTime;
	}

	/**
	 * @param stepTime The stepTime to set.
	 */
	public Toaster setStepTime(int stepTime)
	{
		this.stepTime = stepTime;
		return this;
	}

	/**
	 * @return Returns the toasterColor.
	 */
	public Color getToasterColor()
	{
		return toasterColor;
	}

	/**
	 * @param toasterColor The toasterColor to set.
	 */
	public Toaster setToasterColor(Color toasterColor)
	{
		this.toasterColor = toasterColor;
		return this;
	}

	/**
	 * @return Returns the toasterHeight.
	 */
	public int getToasterHeight()
	{
		return toasterHeight;
	}

	/**
	 * @param toasterHeight The toasterHeight to set.
	 */
	public Toaster setToasterHeight(int toasterHeight)
	{
		this.toasterHeight = toasterHeight;
		return this;
	}

	/**
	 * @return Returns the toasterWidth.
	 */
	public int getToasterWidth()
	{
		return toasterWidth;
	}

	/**
	 * @param toasterWidth The toasterWidth to set.
	 */
	public Toaster setToasterWidth(int toasterWidth)
	{
		this.toasterWidth = toasterWidth;
		return this;
	}

	/**
	 * 
	 * @param args
	 */
	static public void main(String args[])
	{
		Toaster toasterManager = new Toaster().setStepTime(20).setStep(20);
		toasterManager.showToaster("JToaster Hello World!");
	}
}
