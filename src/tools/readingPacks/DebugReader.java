package tools.readingPacks;

import java.io.File;
import java.util.Scanner;

public class DebugReader extends ReadingTool<String> {
	public DebugReader(File filePath) {
		super(filePath);
	}

	@Override
	public String read() throws Exception {
		String str = "";
		Scanner sc = new Scanner(workingOn);
		while (sc.hasNextLine()) {
			str += sc.nextLine();
		}
		sc.close();
		return str;
	}
}
