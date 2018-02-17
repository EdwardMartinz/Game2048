package logic;

import java.util.Random;


public interface GameRules {
	
	 // Undefined methods
	void addNewCellAt(Board board, Position pos, Random rand);
	
	int merge(Cell self, Cell other);
	
	int getBestValue(Board board);
	
	boolean win(Board board);
	
	// Default Methods
	default boolean lose(Board board) {
		return (!board.canMerge(this) 
				&& board.currentEmptyCells().numOfEmptyCells() == 0);
	}
	
	default Board createBoard(int size) { 
		return new Board(size);
	}
	
	default void addNewCell(Board board, Random rand) {
		Position emptyP = EmptyCellsList.choice(board.currentEmptyCells(),rand);
		addNewCellAt(board,emptyP,rand);
	}
	
	default void initBoard(Board board, int numCells, Random rand) {
		board.initialize();
		for (int i = 0; i < numCells; i++) {
			addNewCell(board, rand);
		}
	}
	
	default boolean canMergeNeighbours(Cell cell1, Cell cell2) {
		return(cell1.getValor() == cell2.getValor()
			&& cell1.getValor()!= 0 && cell2.getValor() != 0);
	}
	
	// 90 % of the time generates num90, 10% num10
	default int generateNumber(Random rand,int num90, int num10) {
		int number = rand.nextInt(10) + 1;		
		return(number <= 9) ? num90 : num10; 
	}
}
