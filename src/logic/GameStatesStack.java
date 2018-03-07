package logic;

import java.util.EmptyStackException;

public class GameStatesStack {
	
	private static final int CAPACITY = 20;
	private GameState[] stack;
	private int stackPointer;

	
	public GameStatesStack() {
		stack = new GameState[CAPACITY];
		stackPointer = 0; // first position free in the stack
	}

	// ClearStack
	public void clearStack() {
		stackPointer = 0;
	}

	// Stores a new state
	public void push(GameState state) {
		
		if (isFull()) {
			for (int i = 0; i < CAPACITY - 1; i++)
				stack[i] = stack[i + 1];

			stackPointer--;
		}
		stack[stackPointer] = state;
		stackPointer++;
	}

	// Returns the last state
	public GameState pop() {
		
		if (isEmpty())
			throw new EmptyStackException(); // exception
		else {
			stackPointer--;
			return stack[stackPointer];
		}
	}

	// Checks if the stack is empty
	public boolean isEmpty() {
		return (stackPointer == 0);
	}

	// Checks if the stack is full
	private boolean isFull() {
		return (stackPointer == CAPACITY);
	}
}
