package logic;

import java.util.Random;

import exceptions.GameOverException;
import utils.MyStringUtils;

public class Game {

	// Constants
	public static final int SIZE = 4;
	public static final int INIT_CELLS = 2;
	// Atributes
	private GameType gameType;
	private GameRules currentRules;
	private Board board;
	private int size;
	private int initCells;
	private Random myRandom;
	private int score;
	private int bestValue;
	private boolean isFinished; 
	private GameStatesStack undoStack;
	private GameStatesStack redoStack;

	// Constructor 
	public Game(GameType gType, int dim, int numCells, long seed) {
		gameType = gType;
		currentRules = gType.getRules();
		isFinished = false; 
		resetGame(dim, numCells, seed);
	}
	
	// Reset
	public void resetGame(int dim, int numCells, long seed) {
		size = dim;
		board = currentRules.createBoard(dim);
		initCells = numCells;
		myRandom = new Random(seed);
		currentRules.initBoard(board, numCells, myRandom);
		undoStack = new GameStatesStack();
		redoStack = new GameStatesStack();
		score = 0;
		bestValue = currentRules.getBestValue(board);
	}

	// Change the rules of the game
	public void changeTheGame(GameType newType, int dim, int numCells, long seed) {
		gameType = newType;
		currentRules = newType.getRules();
		resetGame(dim, numCells, seed);
	}

	// Executes a move
	public void move(Direction dir) throws GameOverException {
		undoStack.push(getState()); // saves the previous state
		MoveResults move = board.executeMove(dir, currentRules);

		if (move.getMoved()) {
			redoStack.clearStack(); 
			currentRules.addNewCell(board, myRandom);
			score = score + move.getPoints();
			bestValue = currentRules.getBestValue(board);
			// Checks if the game is over
			if (gameIsOver()) {
				isFinished = true;
				if(win())
					throw new GameOverException("\nYou Win !! ");
				else if (lose())
					throw new GameOverException("\nYou Lose !! ");
			}	
		}
	}

	// Checks if the game is over
	public boolean gameIsOver() {
		return (win() || lose());
	}

	public boolean win() {
		return (currentRules.win(board));
	}

	public boolean lose() {
		return (currentRules.lose(board));
	}

	// Returns if the game is finished
	public boolean isFinished() {
		return isFinished;
	}
	
	// To end the game
	public void FinishTheGame() {
		isFinished = true;
	}

	// GameState
	public GameState getState() {
		return new GameState(score, board.getState());
	}

	public void setState(GameState aState) {
		score = aState.getScore();
		board.setState(aState.getBoardState());
		bestValue = currentRules.getBestValue(board);
	}

	// Undo
	public void undo() {
		GameState previousState = undoStack.pop();
		redoStack.push(getState()); // puts actual state en redos stack
		setState(previousState);
	}

	// Redo
	public void redo() {
		GameState redoState = redoStack.pop();
		undoStack.push(getState()); // puts actual state in undos Stack
		setState(redoState);
	}
	
	// Getters
	public int getSize() {
		return size;
	}

	public int getInitCells() {
		return initCells;
	}

	// toString
	public String toString() {
		return "\n" + gameType.getDescription() + "\n" + board 
				+ "best value: " + bestValue 
				+ MyStringUtils.repeat(" ", size) + "score: " + score;
	}
}
