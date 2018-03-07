package logic;

// Class that represents the results of a movement
public class MoveResults {

	private boolean moved;
	private int points;

	public MoveResults(boolean moved, int points) {
		this.moved = moved;
		this.points = points;
	}

	public boolean getMoved() {
		return moved;
	}

	public int getPoints() {
		return points;
	}

}
