package UI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prgm.events.senders.OnTextSizeChangeSender;
import prgm.events.subscribers.OnTextSizeChange;
import prgm.prgmInfo.InitValues;

public class SettingPan extends JPanel implements OnTextSizeChange {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6460829959376157332L;
	private Objs objs;
	private class Objs {
		//Configurations		
		private GridLayout gridLayout = new GridLayout(1, 2);
		private String cannedTextOfSizeMessage = "Text Size %d";
		
		//Components
		private JLabel TextSizeMessage;
		private JSlider SizeSlider;
		
		void populate() {
			TextSizeMessage = new JLabel();
			SizeSlider = new JSlider(JSlider.HORIZONTAL);
			
			TextSizeMessage.setText(String.format(cannedTextOfSizeMessage, Values.textSize));
			TextSizeMessage.setFont(InitValues.DEFAULTFONT);
			
			SizeSlider.setValue(InitValues.INITTEXTSIZE);
			SizeSlider.setSnapToTicks(true);
			SizeSlider.setMinimum(8);
			SizeSlider.setMaximum(64);
			SizeSlider.setPaintTicks(true);
			SizeSlider.setMajorTickSpacing(1);
			SizeSlider.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					Values.textSize = SizeSlider.getValue();
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
	}
	
	public static class Values {
		public static int textSize = InitValues.INITTEXTSIZE;
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
		objs.getTextSizeMessage().setFont(new Font(InitValues.TEXTFONTS, InitValues.TEXTFONTVALUE, newSizeValue));
		objs.getTextSizeMessage().setText(String.format(objs.cannedTextOfSizeMessage, newSizeValue));
	}

	public void postInit() {
		OnTextSizeChangeSender.subscribe(this);		
	}
}
