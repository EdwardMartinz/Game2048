package logic;

public class GameState {

	private int score;
	private int[][] boardState;

	public GameState(int score, int[][] boardState) {
		this.score = score;
		this.boardState = boardState;
	}

	public int getScore() {
		return score;
	}

	public int[][] getBoardState() {
		return boardState;
	}

}
