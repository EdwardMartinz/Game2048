package commands;

import java.util.EmptyStackException;

import exceptions.ExecuteException;
import logic.Game;

public class UndoCommand extends NoParamsCommand {
	
	// Exception
	public static final String UNDO_EXCEPTION = "Undo is not available\n";
	
	// Attributes
	private final static String COMMAND_INFO = "undo";
	private final static String HELP_INFO = "undo the last command\n";


	// Constructor
	public UndoCommand() {
		super(COMMAND_INFO, HELP_INFO);
	}

	// Execute
	@Override
	public boolean execute(Game game) throws ExecuteException {
		try {
			game.undo();
			System.out.println("Undoing one move...\n");
			
		}catch(EmptyStackException es) {
			throw new ExecuteException(UNDO_EXCEPTION);
		}
		return true;
	}
}
