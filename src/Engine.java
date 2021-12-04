import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import UI.Window;
import tools.FunctionProp;
import tools.FunctionProp.AnsType;
import tools.Helpers;
import tools.ReadingTool;

public class Engine implements Serializable {
	public class UIElements {
		private JButton LoadButton;
		private BorderLayout layout;
		private JTabbedPane tabbedPane;
		private JPanel defaultPane;
		private ArrayList<JPanel> jPanels;
		private ArrayList<ArrayList<FunctionProp>> functionProps;

		public UIElements() {
			LoadButton = new JButton();
			layout = new BorderLayout();
			tabbedPane = new JTabbedPane();
			defaultPane = new JPanel(true);
			jPanels = new ArrayList<>(1);
			functionProps = new ArrayList<>(1);
		}

		public ArrayList<ArrayList<FunctionProp>> getFunctionProps() {
			return functionProps;
		}

		public ArrayList<JPanel> getjPanels() {
			return jPanels;
		}

		public JPanel getDefaultPane() {
			return defaultPane;
		}

		public JButton getLoadButton() {
			return LoadButton;
		}

		public BorderLayout getLayout() {
			return layout;
		}

		public JTabbedPane getTabbedPane() {
			return tabbedPane;
		}
	}

	public class UIBehavior {
		private int WindowClosing;

		public UIBehavior() {
			WindowClosing = JFrame.EXIT_ON_CLOSE;
		}

		public int getWindowClosing() {
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
	private final Runnable[] runnables = { new Runnable() {

		@Override
		public void run() {
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
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
					jfc.showOpenDialog(window.getSelf());
					ReadingTool rt = new ReadingTool(jfc.getSelectedFile());
					ArrayList<FunctionProp> functionProps = new ArrayList<>(1);
					try {
						functionProps = rt.read();
					} catch (ParserConfigurationException | SAXException | IOException e1) {
						e1.printStackTrace();
					}
					ArrayList<JPanel> list = elementsOnThis.getjPanels();
					elementsOnThis.getFunctionProps().add(functionProps);
					JPanel generated = buildPanel(functionProps);
					list.add(generated);
					JScrollPane jsp = new JScrollPane(generated);
					jTabbedPane.add(jsp,
							makeName(rt, jTabbedPane), jTabbedPane.getTabCount());
				}
				
				private String makeName(final ReadingTool readingTool, final JTabbedPane jtp) {
					String Name = readingTool.getWorkingOn().getName().substring(0, readingTool.getWorkingOn().getName().indexOf("."));
					String NewName = "";
					if (jtp.indexOfTab(Name) != -1) {
						for (int i = 1; i < Integer.MAX_VALUE; i ++) {
							NewName = String.format("%s(%d)", Name, i);
							if (jtp.indexOfTab(NewName) == -1) {
								return NewName;
							}
						}
					}
					return Name;
				}
			});
			panel.add(button);
			jTabbedPane.add("Home", panel);
			window.getSelf().add(elementsOnThis.getTabbedPane(), BorderLayout.CENTER);

		}
	} };

	public Engine() {
		window = new Window("Math UI Engine");
		if (!window.toggleAfterRunnable(runnables, false)) {
			JOptionPane.showMessageDialog(null, "Error: Could Not Inintailize Engine", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		self = this;
	}

	private JPanel buildPanel(final ArrayList<FunctionProp> functionProps) {
		JPanel jPanel = new JPanel(true);
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < functionProps.size(); i++) {
			FunctionProp functionProp = functionProps.get(i);
			JButton button = new JButton(functionProp.getName());
			button.setName("Ind=" + i + "|");
			button.setToolTipText(functionProp.getToolTipMessage());
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GridLayout layout = new GridLayout(functionProp.getInputFieldCount() + 3, 1);
					JPanel pan = new JPanel(layout, true);
					JLabel name = new JLabel(functionProp.getName(), JLabel.CENTER);
					pan.add(name);
					JTextField[] textFields = new JTextField[functionProp.getInputFieldCount()];
					for (int i = 0; i < textFields.length; i++) {
						textFields[i] = new JTextField();
						pan.add(textFields[i]);
					}
					JTextArea textArea = new JTextArea();
					textArea.setEditable(false);
					textArea.setLineWrap(true);
					textArea.setBorder(LineBorder.createBlackLineBorder());
					JButton enter = new JButton("Enter");
					enter.addActionListener(/**
											 * @author also me
											 *
											 */
							new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									boolean good = false;
									for (int j = 0; j < textFields.length; j++) {
										good = (textFields[j].getText().matches("\\d"));
									}
									if (good) {
										try {
											float[] fs = new float[functionProp.getInputFieldCount()];
											for (int i = 0; i < fs.length; i++) {
												fs[i] = Float.parseFloat(textFields[i].getText());
											}
											String result = run(functionProp.getLaunchArg(), fs);
											if (functionProp.getType().equals(AnsType.FULL)) {
												textArea.setText(result);
											} else {
												textArea.setText("The answer is: " + result);
											}
										} catch (IOException | InterruptedException e1) {
											textArea.setText("Error: cannot run command");
											e1.printStackTrace();
										}
									}
								}

								private String run(String launchArg, float[] args)
										throws IOException, InterruptedException {
									String arg = launchArg.substring(launchArg.indexOf("{") + 1,
											launchArg.indexOf("}"));
									arg = arg.replace("[values]", Helpers.getContentOfFloatArray(args));
									Runtime rt = Runtime.getRuntime();
									Process process = rt.exec(arg);
									String ret = "";
									process.waitFor();
									Scanner sc = new Scanner(
											new BufferedReader(new InputStreamReader(process.getInputStream())));
									while (sc.hasNext()) {
										ret += sc.next();
									}
									sc.close();
									if (ret.isEmpty()) {
										ret = "No Answer, Program Selected is either not working correctly or is not set up";
									}
									return ret;
								}
							});
					pan.add(enter);
					pan.add(textArea);
					JOptionPane.showMessageDialog(window.getSelf(), pan, functionProp.getName(),
							JOptionPane.PLAIN_MESSAGE);
				}
			});
			jPanel.add(button, -1);
		}
		return jPanel;
	}

	public static void main(String[] args) {
		new Engine();
	}

}
