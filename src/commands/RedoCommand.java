package commands;

import java.util.EmptyStackException;

import exceptions.ExecuteException;
import logic.Game;

public class RedoCommand extends NoParamsCommand {

	// Exception
	public static final String REDO_EXCEPTION = "Nothing to redo\n";

	// Attributes
	private final static String COMMAND_INFO = "redo";
	private final static String HELP_INFO = "redo the last undone command\n";

	public RedoCommand() {
		super(COMMAND_INFO, HELP_INFO);
	}

	@Override
	public boolean execute(Game game) throws ExecuteException {
		try {
			game.redo();
			System.out.println("Redoing one move...\n");
			
		} catch (EmptyStackException es) {
			throw new ExecuteException(REDO_EXCEPTION);
		}
		return true;
	}
}
