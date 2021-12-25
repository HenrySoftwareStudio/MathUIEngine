package tools.readingPacks;

import java.io.File;

public abstract class ReadingTool<T> {
	protected final File workingOn;

	public ReadingTool(final File filePath) {
		if (filePath != null) {
			workingOn = filePath;
		} else {
			throw new IllegalArgumentException("filePath Argument Can not be null");
		}
	}

	public abstract T read() throws Exception;

	public File getWorkingOn() {
		return workingOn;
	}
}
