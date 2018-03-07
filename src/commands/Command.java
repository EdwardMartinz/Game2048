package commands;

import java.util.Scanner;

import exceptions.CommandParseException;
import exceptions.ExecuteException;
import exceptions.GameOverException;
import logic.Game;

public abstract class Command {

	private String helpText;
	private String commandText;
	protected final String commandName;

	public Command(String commandInfo, String helpInfo) {
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];
	}

	// Executes a Command 
	// Returns true to print the game, false to not print the game
	public abstract boolean execute(Game game) throws ExecuteException, GameOverException;
	
	// Parse a command
	public abstract Command parse(String[] commandWords, Scanner in) throws CommandParseException;

	// Prints the help message
	public String helpText() {
		return " " + commandText + ": " + helpText;
	}
}
