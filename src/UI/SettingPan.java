package UI;

import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prgm.events.senders.OnTextSizeChangeSender;
import prgm.events.subscribers.OnTextSizeChange;
import prgm.prgmInfo.InitValues;
import tools.SettingProperty;

@SuppressWarnings("deprecation")
public class SettingPan extends JPanel implements OnTextSizeChange {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6460829959376157332L;
	public static final File SETTINGFILE = new File("Settings.xml");
	private Objs objs;
	private class Objs {
		//Configurations		
		private GridLayout gridLayout = new GridLayout(1, 2);
		private String cannedTextOfSizeMessage = "Text Size %d";
		private SettingProperty TextSize = new SettingProperty();
		private int TextSizeOld = InitValues.INITTEXTSIZE;
		
		//Components
		private JLabel TextSizeMessage;
		private JSlider SizeSlider;
		
		void populate() {
			TextSizeMessage = new JLabel();
			SizeSlider = new JSlider(JSlider.HORIZONTAL);
			
			TextSizeMessage.setText(String.format(cannedTextOfSizeMessage, Values.textSize));
			TextSizeMessage.setFont(InitValues.DEFAULTFONT);
			
			SizeSlider.setSnapToTicks(true);
			SizeSlider.setMinimum(8);
			SizeSlider.setMaximum(64);
			SizeSlider.setPaintTicks(true);
			SizeSlider.setMajorTickSpacing(1);
			SizeSlider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					OnTextSizeChangeSender.send(Values.textSize);
				}
			});
		}
		
		public JSlider getSizeSlider() {
			return SizeSlider;
		}
		
		public JLabel getTextSizeMessage() {
			return TextSizeMessage;
		}
		
		public GridLayout getGridLayout() {
			return gridLayout;
		}
		
		public SettingProperty getTextSize() {
			return TextSize;
		}
		
		public void setTextSize(SettingProperty textSize) {
			TextSize = textSize;
		}
		
		public int getTextSizeOld() {
			return TextSizeOld;
		}

		public void setTextSizeOld(int textSizeOld) {
			TextSizeOld = textSizeOld;
		}
	}

	public static class Values {
		public static int textSize = 0;
	}

	public SettingPan() {
		this(true);
	}

	private SettingPan(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		objs = new Objs();
		setup();
	}

	private void setup() {
		objs.populate();
		setLayout(objs.getGridLayout());
		this.add(objs.getTextSizeMessage());
		this.add(objs.getSizeSlider());
	}

	@Override
	public void onTSCEvent(int newSizeValue) {
		this.objs.setTextSizeOld(Values.textSize);
		Values.textSize = this.objs.getSizeSlider().getValue();
		firePropertyChange("TextSizeChange", this.objs.getTextSizeOld(), Values.textSize);
		objs.getTextSizeMessage().setFont(new Font(InitValues.TEXTFONTS, InitValues.TEXTFONTVALUE, newSizeValue));
		objs.getTextSizeMessage().setText(String.format(objs.cannedTextOfSizeMessage, newSizeValue));
		objs.getTextSize().setValue(newSizeValue + "");
		
	}

	public void postInit() {
		OnTextSizeChangeSender.subscribe(this);		
	}

	public SettingProperty getTextSizeWrap() {
		return this.objs.getTextSize();
	}

	public void setTextSizeWrap(SettingProperty textSize) {
		this.objs.setTextSize(textSize);
		OnTextSizeChangeSender.send(Integer.valueOf(textSize.getValue()));
	}
}
