package exceptions;

@SuppressWarnings("serial")
public class GameOverException extends Exception {

	public GameOverException() {
		super();
	}

	public GameOverException(String message) {
		super(message);
	}
	
}
