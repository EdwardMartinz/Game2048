package logic;

import java.util.Random;

public class GameRulesInverse implements GameRules {

	// Win value
	private static final int WIN_VALUE = 1;
	// Initial values
	private static final int INIT_VALUE_A = 2048;
	private static final int INIT_VALUE_B = 1024;

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		board.setCell(pos, generateNumber(rand, INIT_VALUE_A, INIT_VALUE_B));
	}

	@Override
	public int merge(Cell self, Cell other) {
		int points = 0;
		
		if (canMergeNeighbours(self, other)) {
			self.setValor(self.getValor() / 2);
			other.setValor(0);
			points = 4096 / self.getValor();
			return points;
		}
		else
			return points; 
	}

	@Override
	public int getBestValue(Board board) {
		return board.lowestValue();
	}

	@Override
	public boolean win(Board board) {
		return (getBestValue(board) == WIN_VALUE);
	}

}
