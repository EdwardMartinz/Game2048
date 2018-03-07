package commands;

import java.util.Scanner;

import exceptions.ExecuteException;
import logic.Game;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);

	}

	// Execute of one parameter commands is in the subclass
	public abstract boolean execute(Game game) throws ExecuteException;

	@Override
	public Command parse(String[] commandWords, Scanner in) {
		if (commandWords.length == 1 && commandWords[0].equals(commandName))
			return this;
		else
			return null;
	}
}
