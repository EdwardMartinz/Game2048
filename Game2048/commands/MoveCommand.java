package commands;

import java.util.Scanner;

import exceptions.CommandParseException;
import exceptions.GameOverException;
import logic.Direction;
import logic.Game;

public class MoveCommand extends Command{
	
	// Move parse exceptions
	public static final String INVALID_DIRECTION = "Unknown direction for move command\n";
	public static final String MOVE_NO_PARAM = "Move must be followed by a direction\n";

	// Atributes
	private Direction dir;
	private final static String COMMAND_INFO = "move <direction>";
	private final static String HELP_INFO ="execute a move"
			+ " in one of the directions:"
			+ " up, down, left, right\n";
	
	// Constructors
	public MoveCommand() {
		super(COMMAND_INFO,HELP_INFO);
	}
	
	public MoveCommand(Direction dir) {
		super(COMMAND_INFO,HELP_INFO);
		this.dir = dir;
	}

	// Execute
	@Override
	public boolean execute(Game game) throws GameOverException {
		game.move(dir);
		return true; 
	}
	
	// Parse
	@Override
	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		if(commandWords.length == 2 && commandWords[0].equals(commandName)) {
			Direction newDir = Direction.contains(commandWords[1]);
			
			if(newDir != null) // validate the direction
				return new MoveCommand(newDir);
			else
				throw new CommandParseException(INVALID_DIRECTION);
			
		}else if(commandWords.length != 2 && commandWords[0].equals(commandName)) {
			throw new CommandParseException(MOVE_NO_PARAM);
			
		}else { return null; }
	}
}
