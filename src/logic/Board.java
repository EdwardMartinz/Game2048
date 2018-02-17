package logic;


import utils.MyStringUtils;

public class Board {

	// Attributes
	private Cell[][] board;
	private int boardSize;

	// Constructor
	public Board(int Bsize) {
		boardSize = Bsize;
		board = new Cell[Bsize][Bsize];
		
	}
	
	// Size
	public int getBoardSize() {
		return boardSize;
	}

	// Changes the value of a cell
	public void setCell(Position pos, int value) {
		board[pos.getRow()][pos.getColumn()].setValor(value);
	}

	// Initializes the board
	public void initialize() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = new Cell(0);
			}
		}
	}

	// Returns the list of empty positions
	public EmptyCellsList currentEmptyCells() {
		EmptyCellsList vacias = new EmptyCellsList(); 
		Position emptyPosition;

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j].isEmpty()) {
					emptyPosition = new Position(i, j);
					vacias.insertar(emptyPosition);
				}
			}
		}

		return vacias;
	}

	// Slide
	public boolean slide() {
		int pos;
		boolean canSlide = false;

		for (int i = 0; i < boardSize; i++) {
			pos = 0;
			for (int j = 0; j < boardSize - 1; j++) {
				if (!board[i][j].isEmpty()) {
					pos++;
				} else if (board[i][j].isEmpty() && !board[i][j + 1].isEmpty()) {
					board[i][pos].setValor(board[i][j + 1].getValor());
					board[i][j + 1].setValor(0);
					canSlide = true;
					pos++;
				}
			}
		}
		return canSlide;
	}

	// Fusion
	public int fusion(GameRules rules) { 
		int points = 0;
		int fusion = 0;

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize - 1; j++) {
				fusion = board[i][j].doMerge(board[i][j + 1], rules);
				if (fusion != 0) { // if there is fusion
					points = points + fusion;
					j++;
				}
			}
		}
		return points;
	}

	// Transposes the board
	public void transpose() {
		Cell[][] transposeBoard = new Cell[boardSize][boardSize];

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				transposeBoard[j][i] = new Cell(0);
				transposeBoard[j][i].setValor(board[i][j].getValor());
			}
		}

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j].setValor(transposeBoard[i][j].getValor());
			}
		}

	}

	// Reflects the board
	public void reflect() {
		boolean stop = false;
		int temp;
		int i = 0;

		while (!stop) {
			for (int j = 0; j < boardSize; j++) {
				temp = board[j][i].getValor();
				board[j][i].setValor(board[j][(boardSize - 1) - i].getValor());
				board[j][(boardSize - 1) - i].setValor(temp);
			}
			i++;

			if (i >= ((boardSize - 1) - i))
				stop = true;
		}
	}
	
	// Determines what is the highest value of the board
	public int highestValue() {
		int highest = 0;
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if(board[i][j].getValor() > highest) {
					highest = board[i][j].getValor();
				}
			}
		}
		return highest;	
	}
	
	// Determines what is the lowest value of the board
	public int lowestValue() {
		int lowest = highestValue();
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if(!board[i][j].isEmpty()
					&& board[i][j].getValor() <= lowest) {
					lowest = board[i][j].getValor();
				}
			}
		}
		return lowest;	
	}
	
	// Movement
	public MoveResults moveLeft(GameRules rules) {
		
		// Slide , fusion, slide to complete a movement
		boolean canSlide = slide();
		int points = fusion(rules);
		slide();

		// If slide or fusion is possible you can move
		boolean moved = (canSlide || points != 0);
		
		return new MoveResults(moved, points);
	}

	// Executes a move in the specified direction
	public MoveResults executeMove(Direction dir,GameRules rules) {
		MoveResults result = null;

		switch (dir) {
		case LEFT:
			result = moveLeft(rules);
			break;

		case RIGHT:
			reflect();
			result = moveLeft(rules);
			reflect();
			break;

		case UP:
			transpose();
			result = moveLeft(rules);
			transpose();
			break;

		case DOWN:
			transpose();
			// move right
			reflect();
			result = moveLeft(rules);
			reflect();
			transpose();
			break;
		}
		return result;
	}
	
	// Checks if you can merge in some direction to know if the game have finished
	public boolean canMerge(GameRules rules) {
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				if(j != boardSize - 1 && i != boardSize - 1) {
					if(rules.canMergeNeighbours(board[i][j],board[i][j + 1]))
						return true;
					if(rules.canMergeNeighbours(board[i][j],board[i + 1][j])) 
						return true;
				}
				else if(j == boardSize - 1 && i != boardSize - 1) {
					if(rules.canMergeNeighbours(board[i][j],board[i + 1][j])) 
						return true;
				
				}else if(i == boardSize - 1 && j != boardSize - 1) {
					if(rules.canMergeNeighbours(board[i][j],board[i][j + 1]))
						return true;
				}
			}
		}
		return false;
	}
	
	// Returns the board's state
	public int[ ][ ] getState(){
		
		int[][] state = new int[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				state[i][j] = board[i][j].getValor();
			}
		}
		
		return state;
	}
	
	// Sets a board state
	public void setState(int[ ][ ] aState) {
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				board[i][j].setValor(aState[i][j]);
			}
		}
	}
	
	
	// toString
	public String toString() {
		// Symbols
		int cellSize = 7;
		String vDelimiter = "|";
		String hDelimiter = "-";
		String resultado = "";
		
		// Board
		for (int i = 0; i < boardSize; i++) {
			resultado += MyStringUtils.repeat(hDelimiter,
					(cellSize + 1) * boardSize) + "-\n"; // prints horizontal line(--)
			for (int j = 0; j < boardSize; j++) {
				resultado += vDelimiter + MyStringUtils.centre(board[i][j].toString(),
						cellSize); // centres and separates numbers	 ( | n | )
			}
			resultado += vDelimiter + "\n"; // last vertical line
		}
		resultado += MyStringUtils.repeat(hDelimiter, // last horizontal line
				(cellSize + 1) * boardSize) + "-\n";

		return resultado;
	}

}
