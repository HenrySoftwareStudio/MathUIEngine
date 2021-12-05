package UI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

import javax.swing.JFrame;

/**
 * @author also me
 *
 */
public class Window implements WindowListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2194441246113836535L;
	private JFrame self;

	public Window(final String SelfName) {
		self = new JFrame(SelfName);
		self.addWindowListener(this);
	}

	public JFrame getSelf() {
		return self;
	}

	public void toggleSelf() {
		self.setVisible(!self.isVisible());
	}

	public boolean toggleAfterRunnable(final Runnable[] runnables, final boolean ParallelRun) {
		try {
			if (ParallelRun) {
				for (Runnable runnable : runnables) {
					new Thread(runnable, "thread " + runnable.toString()).run();
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

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

}
