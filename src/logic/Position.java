package logic;

public class Position {

	private int row;
	private int column;

	public Position(int fila, int columna) {
		row = fila;
		column = columna;
	}

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
