package exceptions;

@SuppressWarnings("serial")
public class CommandParseException extends Exception{
	
	// Constructors
	public CommandParseException() {
		super();
	}

	public CommandParseException(String message) {
		super(message);
	}
}
