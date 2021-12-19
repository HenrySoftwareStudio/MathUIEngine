package tools;

public class  Utilities {
	public static int countCharRepeat(final String input, final char toBeCounted) {
		int toReturn = 0;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			toReturn += (c == toBeCounted) ? 1 : 0;
		}
		return toReturn;
	}

	/**
	 * @param fs
	 * @return
	 */
	public static String getContentOfFloatArray(float[] fs) {
		String rt = "";
		for (float f : fs) {
			rt = rt + f + " ";
		}
		return rt;
	}
}
