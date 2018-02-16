package logic;

public enum Direction {

	UP, DOWN, LEFT, RIGHT;

	// Tests if a given string matches any Enum
	public static Direction contains(String test) {
		for (Direction dir : Direction.values()) {
			if (dir.name().equalsIgnoreCase(test)) {
				return dir;
			}
		}
		return null;
	}
}
