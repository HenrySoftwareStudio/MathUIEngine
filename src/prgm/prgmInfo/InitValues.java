package prgm.prgmInfo;

import java.awt.Font;
import java.io.File;

public class InitValues {
	@Deprecated
	public static final int INITTEXTSIZE = 14;
	
	//UI Text Info
	public static final String TEXTFONTS = "Plain";
	public static final int TEXTFONTVALUE = Font.PLAIN;
	public static final Font DEFAULTFONT = new Font(TEXTFONTS, TEXTFONTVALUE, INITTEXTSIZE);
	
	//IO related Info
	public static final File SETTINGFILE = new File("Settings.xml");
}
