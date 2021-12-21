package UI;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class SettingPan extends JPanel {
	private class Objs {
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6460829959376157332L;

	public SettingPan() {
		this(true);
	}

	public SettingPan(LayoutManager layout) {
		this(layout, true);
	}

	public SettingPan(boolean isDoubleBuffered) {
		this(null, isDoubleBuffered);
	}

	public SettingPan(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		setup();
	}
	
	private void setup() {
		Objs.class.equals(accessibleContext);
	}

}
