package logic;

public class Position {

	// Attributes
	private int row;
	private int column;

	// Constructor
	public Position(int fila, int columna) {
		row = fila;
		column = columna;
	}

	// Getters and Setters
	public int getRow() {
		return row;
	}

	public void setRow(int fila) {
		row = fila;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int columna) {
		column = columna;
	}

}
