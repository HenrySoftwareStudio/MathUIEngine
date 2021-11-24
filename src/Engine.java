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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Engine implements Serializable
{
	public class UIElements
	{
		private JButton LoadButton;
		private BorderLayout layout;
		private JTabbedPane tabbedPane;
		private JPanel defaultPane;
		
		public UIElements()
		{
			LoadButton = new JButton();
			layout = new BorderLayout();
			tabbedPane = new JTabbedPane();
			defaultPane = new JPanel(true);
		}
		
		public JPanel getDefaultPane()
		{
			return defaultPane;
		}
		
		public JButton getLoadButton()
		{
			return LoadButton;
		}
		
		public BorderLayout getLayout()
		{
			return layout;
		}
		
		public JTabbedPane getTabbedPane()
		{
			return tabbedPane;
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
						JTabbedPane jTabbedPane = elementsOnThis.getTabbedPane();
						jTabbedPane.setTabPlacement(JTabbedPane.TOP);
						JPanel panel = elementsOnThis.getDefaultPane();
						JButton button = elementsOnThis.getLoadButton();
						button.setText("Load Tool Set");
						button.addActionListener(new ActionListener()
						{
							
							@Override
							public void actionPerformed(ActionEvent e)
							{
								JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
								jfc.showOpenDialog(window.getSelf());
								new ReadingTool(jfc.getSelectedFile());								
							}
						});
						panel.add(button);
						jTabbedPane.add("default panel",panel);
						window.getSelf().add(elementsOnThis.getTabbedPane(), BorderLayout.CENTER);
						
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
