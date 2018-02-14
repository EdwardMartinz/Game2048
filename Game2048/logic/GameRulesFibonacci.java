package logic;

import java.util.Random;

public class GameRulesFibonacci implements GameRules {
	
	// Win value
	private static final int WIN_VALUE = 144;
	// Initial values
	private static final int INIT_VALUE_A = 1;
	private static final int INIT_VALUE_B = 2;
	
	@Override
	public void addNewCellAt(Board board, Position pos, Random rand) {
		board.setCell(pos,generateNumber(rand,INIT_VALUE_A,INIT_VALUE_B));
	}

	@Override
	public int merge(Cell self, Cell other) {
		int points = 0;
		
		if(canMergeNeighbours(self, other)){
			points = self.getValor() + other.getValor();
			self.setValor(points);
			other.setValor(0);
			return points;
		}
		else
			return points; // no Fusion
	}
	
	public boolean canMergeNeighbours(Cell cell1, Cell cell2) {
		return(cell1.getValor() == nextFib(cell2.getValor()) 
		|| cell2.getValor() == nextFib(cell1.getValor()) 
		|| cell1.getValor() == 1 && cell2.getValor() == 1);
	}

	// Returns the next Fibonnacci number
	private int nextFib(int previous) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);
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
