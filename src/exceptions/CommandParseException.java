package exceptions;

@SuppressWarnings("serial")
public class CommandParseException extends Exception{
	
	public CommandParseException() {
		super();
	}

	public CommandParseException(String message) {
		super(message);
	}
}
