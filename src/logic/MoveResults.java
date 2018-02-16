package logic;

// Class for encapsulate data
public class MoveResults {

	private boolean moved;
	private int points;

	// Constructor
	public MoveResults(boolean moved, int points) {
		this.moved = moved;
		this.points = points;
	}

	// Getters
	public boolean getMoved() {
		return moved;
	}

	public int getPoints() {
		return points;
	}

}
