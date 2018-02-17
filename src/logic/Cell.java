package logic;

public class Cell {

	// Attributes
	private int valor;

	// Constructor
	public Cell(int value) {
		valor = value;
	}

	// Getters and Setters
	public int getValor() {
		return valor;
	}

	public void setValor(int value) {
		valor = value;
	}

	// Checks if a cell is empty
	public boolean isEmpty() {
		return (valor == 0);
	}

	// Checks if two cells can merge
	public int doMerge(Cell neighbour, GameRules rules) {
		return rules.merge(this, neighbour);
	}
	
	// toString
	public String toString() {
		if (valor == 0)
			return " ";
		else
			return "" + valor;
	}

}
