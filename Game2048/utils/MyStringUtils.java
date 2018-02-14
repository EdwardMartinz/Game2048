package utils;

// Helps to print the interface of the game
public class MyStringUtils {

	// Repeats a string a number of times(length)
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}

	// Centres a string
	public static String centre(String text, int len) {

		String out = String.format(" %" + len + "s %s %" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;
		return out.substring((int) start, (int) end);
	}

}
