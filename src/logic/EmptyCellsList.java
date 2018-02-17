package logic;

import java.util.Random;

// List of empty positions on the board
public class EmptyCellsList {

	// Attributes
	private final static int CAPACIDAD = 400; 
	private Position[] arrayAsList;
	private int cont;

	// Constructor
	public EmptyCellsList() {
		arrayAsList = new Position[CAPACIDAD];
		cont = 0;
	}
	
	// Returns the size of the list
	public int numOfEmptyCells() {
		return cont;
	}

	// Inserts an element into the list
	public void insertar(Position p) {
		if (!isFull()) {
			arrayAsList[cont] = p;
			cont++;
		}
	}

	// Checks if the list is full
	private boolean isFull() {
		return (cont == CAPACIDAD);
	}

	// Returns the Object in the position nextInt in the list
	private Position get(int nextInt) {
		return arrayAsList[nextInt];
	}

	// Chooses an object randomly in the list
	public static Position choice(EmptyCellsList list, Random random) {
		return list.get(random.nextInt(list.numOfEmptyCells()));
	}

	

}
