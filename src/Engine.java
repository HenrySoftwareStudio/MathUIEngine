import UI.Window;
import tools.FunctionProp;
import tools.ReadingTool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Engine implements Serializable
{
	public class UIElements
	{
		private JButton LoadButton;
		private BorderLayout layout;
		private JTabbedPane tabbedPane;
		private JPanel defaultPane;
		private ArrayList<JPanel> jPanels;
		private ArrayList<ArrayList<FunctionProp>> functionProps;
		
		public UIElements()
		{
			LoadButton = new JButton();
			layout = new BorderLayout();
			tabbedPane = new JTabbedPane();
			defaultPane = new JPanel(true);
			jPanels = new ArrayList<>(1);
			functionProps = new ArrayList<>(1);
		}
		
		public ArrayList<ArrayList<FunctionProp>> getFunctionProps()
		{
			return functionProps;
		}
		
		public ArrayList<JPanel> getjPanels()
		{
			return jPanels;
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
						window.getSelf().setLocationRelativeTo(null);
						window.getSelf().setMinimumSize(new Dimension(400, 400));
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
								ReadingTool rt = new ReadingTool(jfc.getSelectedFile());
								ArrayList<FunctionProp> functionProps = new ArrayList<>(1);
								try
								{
									functionProps = rt.read();
								}
								catch (ParserConfigurationException | SAXException | IOException e1)
								{
									e1.printStackTrace();
								}
								ArrayList<JPanel> list = elementsOnThis.getjPanels();
								elementsOnThis.getFunctionProps().add(functionProps);
								JPanel generated = buildPanel(functionProps);
								list.add(generated);
								jTabbedPane.add(generated, rt.getWorkingOn().getName());
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
	
	private JPanel buildPanel(final ArrayList<FunctionProp> functionProps)
	{
		JPanel jPanel = new JPanel(true);
		jPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
		for (int i = 0; i < functionProps.size(); i++)
		{
			FunctionProp functionProp = functionProps.get(i);
			JButton button = new JButton(functionProp.getName());
			button.setName("Ind=" + i + "|");
			button.setToolTipText(functionProp.getToolTipMessage());
			button.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					GridLayout layout = new GridLayout(functionProp.getInputFieldCount() + 3, 1);
					JPanel pan = new JPanel(layout, true);
					JLabel name = new JLabel(functionProp.getName(), JLabel.CENTER);
					pan.add(name);
					JTextField[] textFields = new JTextField[functionProp.getInputFieldCount()];
					for(int i = 0; i < textFields.length; i++)
					{ 
						textFields[i] = new JTextField();
						pan.add(textFields[i]);
					}
					JTextArea textArea = new JTextArea();
					textArea.setEditable(false);
					JButton enter = new JButton("Enter");
					enter.addActionListener(new ActionListener()
					{
						
						@Override
						public void actionPerformed(ActionEvent e)
						{
							// TODO Auto-generated method stub
							
						}
					});
					pan.add(enter);
					pan.add(textArea);
					JOptionPane.showMessageDialog(window.getSelf(), pan, functionProp.getName(), JOptionPane.PLAIN_MESSAGE);					
				}
			});
			jPanel.add(button, -1);
		}
		return jPanel;
	}

	public static void main(String[] args)
	{
		new Engine();
	}

}
