package tools.readingPacks;

import java.io.File;

public abstract class ReadingTool<T> {
	protected final File workingOn;

	public ReadingTool(final File filePath) {
		workingOn = filePath;
	}

	public abstract T read() throws Exception;

	public File getWorkingOn() {
		return workingOn;
	}
}
