package UI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

import javax.swing.JFrame;

import prgm.events.senders.OnTextSizeChangeSender;
import prgm.events.subscribers.OnTextSizeChange;

/**
 * @author also me
 *
 */
public class Window implements WindowListener, Serializable, OnTextSizeChange {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2194441246113836535L;
	private JFrame self;

	public Window(final String SelfName) {
		self = new JFrame(SelfName);
		OnTextSizeChangeSender.subscribe(this);
		self.addWindowListener(this);
	}

	public JFrame getSelf() {
		return self;
	}

	public void toggleSelf() {
		self.setVisible(!self.isVisible());
	}
	
	public void repaint() {
		self.repaint();
	}

	public boolean toggleAfterRunnable(final Runnable[] runnables, final boolean ParallelRun) {
		try {
			if (ParallelRun) {
				for (Runnable runnable : runnables) {
					new Thread(runnable, "thread " + runnable.toString()).start();
				}
			} else {
				for (Runnable runnable : runnables) {
					runnable.run();
				}
			}
		} catch (Exception e) {
			return false;
		}
		toggleSelf();
		return true;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		//No Use Yet
	}

	@Override
	public void windowClosing(WindowEvent e) {
		//No Use Yet
	}

	@Override
	public void windowClosed(WindowEvent e) {
		//No Use Yet
	}

	@Override
	public void windowIconified(WindowEvent e) {
		//No Use Yet
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		//No Use Yet
	}

	@Override
	public void windowActivated(WindowEvent e) {
		//No Use Yet
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		//No Use Yet
	}

	@Override
	public void onTSCEvent(int newSizeValue) {
		this.self.repaint();
	}
}
