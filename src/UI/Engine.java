package UI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
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

import tools.ChangableString;
import tools.FunctionProp;
import tools.FunctionProp.AnsType;
import tools.Utils;
import tools.readingPacks.FunctionPackReadingTool;
import tools.readingPacks.UITextReadingTool;

public class Engine implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6276476245807073086L;
	private UIElements elementsOnThis;
	private Window window;
	
	public class UIElements {
		private UITextReadingTool UIreadingTool;
		private JButton LoadButton;
		private SettingPan SettingPan;
		private BorderLayout layout;
		private JTabbedPane tabbedPane;
		private JPanel defaultPane;
		private ArrayList<JPanel> jPanels;
		private ArrayList<ArrayList<FunctionProp>> functionProps;
		private ArrayList<Map<String, ChangableString>> listofText;

		public UIElements() {
			LoadButton = new JButton();
			UIreadingTool = new UITextReadingTool(new File("ui.xml"), new String[] {});
			listofText = new ArrayList<>(0);
			SettingPan = new SettingPan(true);
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
		
		public SettingPan getSettingPan() {
			return SettingPan;
		}
		
		public ArrayList<Map<String, ChangableString>> getListofText() {
			return listofText;
		}
		
		public void setListofText(ArrayList<Map<String, ChangableString>> listofText) {
			this.listofText = listofText;
		}
		
		public UITextReadingTool getUIreadingTool() {
			return UIreadingTool;
		}
	}
	
	public Engine() {
		window = new Window("Math UI Engine");
		try {
			setup();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Could Not Inintailize Engine", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		window.toggleSelf();
	}

	private void setup() throws Exception {
		elementsOnThis = new UIElements();
		elementsOnThis.setListofText(elementsOnThis.getUIreadingTool().read());
		window.getSelf().setLayout(elementsOnThis.getLayout());
		window.getSelf().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getSelf().setLocationRelativeTo(null);
		window.getSelf().setMinimumSize(new Dimension(400, 400));
		JTabbedPane jTabbedPane = elementsOnThis.getTabbedPane();
		jTabbedPane.setTabPlacement(JTabbedPane.TOP);
		JPanel panel = elementsOnThis.getDefaultPane();
		SettingPan setPanel = elementsOnThis.getSettingPan();//settingpan doesn't need to be set up as it is porpuse built and consturctor will do the setting up
		JButton button = elementsOnThis.getLoadButton();
		button.setText("Load Tool Set");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
				jfc.showOpenDialog(window.getSelf());
				FunctionPackReadingTool rt = new FunctionPackReadingTool(jfc.getSelectedFile());
				ArrayList<FunctionProp> functionProps = new ArrayList<>(1);
				try {
					functionProps = rt.read();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				ArrayList<JPanel> list = elementsOnThis.getjPanels();
				elementsOnThis.getFunctionProps().add(functionProps);
				JPanel generated = buildPanel(functionProps);
				list.add(generated);
				JScrollPane jsp = new JScrollPane(generated);
				jTabbedPane.add(jsp,
						makeName(rt, jTabbedPane), jTabbedPane.getTabCount() - 1);
			}
			
			private String makeName(final FunctionPackReadingTool readingTool, final JTabbedPane jtp) {
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
		jTabbedPane.add("Setting", setPanel);
		window.getSelf().add(elementsOnThis.getTabbedPane(), BorderLayout.CENTER);
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
					JScrollPane jScrollPane = new JScrollPane(textArea);
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
									arg = arg.replace("[values]", Utils.getContentOfFloatArray(args));
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
					pan.add(jScrollPane);
					JOptionPane.showMessageDialog(window.getSelf(), pan, functionProp.getName(),
							JOptionPane.PLAIN_MESSAGE);
				}
			});
			jPanel.add(button, -1);
		}
		return jPanel;
	}
}
