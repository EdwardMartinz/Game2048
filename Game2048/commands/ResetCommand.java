package commands;

import java.util.Random;

import logic.Game;

public class ResetCommand extends NoParamsCommand {

	private final static String COMMAND_INFO = "reset";
	private final static String HELP_INFO = "start a new game\n";

	// Constructor
	public ResetCommand() {
		super(COMMAND_INFO, HELP_INFO);
	}

	// Execute
	@Override
	public boolean execute(Game game) {
		long newSeed = new Random().nextInt(1000); // creates a new seed
		game.resetGame(game.getSize(), game.getInitCells(), newSeed); // resets the game
		return true;
	}

}
