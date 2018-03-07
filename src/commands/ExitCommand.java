package commands;


import logic.Game;

public class ExitCommand extends NoParamsCommand {

	private final static String COMMAND_INFO = "exit";
	private final static String HELP_INFO = "terminate the program\n";

	public ExitCommand() {
		super(COMMAND_INFO, HELP_INFO);
	}

	@Override
	public boolean execute(Game game) {
		game.FinishTheGame();
		return false;
	}

}
