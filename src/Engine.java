import UI.Window;
import tools.ReadingTool;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Engine implements Serializable
{
	public class UIElements
	{
		private JButton LoadButton;
		private BorderLayout layout;
		
		public UIElements()
		{
			LoadButton = new JButton();
			layout = new BorderLayout();
		}
		
		public JButton getLoadButton()
		{
			return LoadButton;
		}
		
		public BorderLayout getLayout()
		{
			return layout;
		}
	}
	public class UIBehavior
	{
		private int WindowClosing;
		public UIBehavior()
		{
			WindowClosing=JFrame.EXIT_ON_CLOSE;
		}
		public int getWindowClosing()
		{
			return WindowClosing;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6276476245807073086L;
	
	public static Engine self;
	private UIElements elementsOnThis;
	private UIBehavior behaviorOnThis;
	private Window window;
	private final Runnable[] runnables=
		{
				new Runnable()
				{
					
					@Override
					public void run()
					{
						elementsOnThis = new UIElements();
						behaviorOnThis = new UIBehavior();
						window.getSelf().setLayout(elementsOnThis.getLayout());
						window.getSelf().setDefaultCloseOperation(behaviorOnThis.getWindowClosing());
						JButton button = elementsOnThis.getLoadButton();
						button.setText("Load Tool Set");
						button.addActionListener(new ActionListener()
						{
							
							@Override
							public void actionPerformed(ActionEvent e)
							{
								JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
								jfc.setVisible(true);
								new ReadingTool(jfc.getSelectedFile());								
							}
						});
						window.getSelf().add(elementsOnThis.getLoadButton(), BorderLayout.CENTER);
						
					}
				}
		};
	public Engine()
	{
		window = new Window("Math UI Engine");
		if (!window.toggleAfterRunnable(runnables, false))
		{
			JOptionPane.showMessageDialog(null, "Error: Could Not Inintailize Engine", "Error", JOptionPane.ERROR_MESSAGE);
		}
		self = this;
	}

	public static void main(String[] args)
	{
		new Engine();
	}

}
