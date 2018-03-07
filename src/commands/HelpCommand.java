package commands;

import logic.Game;
import utils.CommandParser;

public class HelpCommand extends NoParamsCommand {

	private final static String COMMAND_INFO = "help";
	private final static String HELP_INFO = "print this help message\n";

	public HelpCommand() {
		super(COMMAND_INFO, HELP_INFO);
	}
	
	@Override
	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;
	}

}
