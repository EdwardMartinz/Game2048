package logic;

import java.util.Random;

public class GameRules2048 implements GameRules {
	
	// Win value
	private static final int WIN_VALUE = 2048;
	// Initial values
	private static final int INIT_VALUE_A = 2;
	private static final int INIT_VALUE_B = 4;

	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		board.setCell(pos, generateNumber(rand, INIT_VALUE_A, INIT_VALUE_B));
	}

	@Override
	public int merge(Cell self, Cell other) {
		int points = 0;
		
		if (canMergeNeighbours(self, other)) {
			points = self.getValor() * 2;
			self.setValor(points);
			other.setValor(0);
			return points;
		}
		else
			return points; 
	}

	@Override
	public int getBestValue(Board board) {
		return board.highestValue();
	}

	@Override
	public boolean win(Board board) {
		return (getBestValue(board) == WIN_VALUE);
	}

}
