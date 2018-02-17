package logic;

public class GameState {

	// Attributes
	private int score;
	private int[][] boardState;

	// Constructor
	public GameState(int score, int[][] boardState) {
		this.score = score;
		this.boardState = boardState;
	}

	// Getters
	public int getScore() {
		return score;
	}

	public int[][] getBoardState() {
		return boardState;
	}

}
