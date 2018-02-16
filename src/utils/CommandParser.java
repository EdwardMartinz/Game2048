package utils;

import java.util.Scanner;

import commands.*;
import exceptions.CommandParseException;


public class CommandParser {

	private static Command[] availableCommands = { 
			new ExitCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new MoveCommand(),
			new UndoCommand(),
			new RedoCommand(),
			new PlayCommand()
	};


	// Returns null or an available Command
	public static Command parseCommand(String[] commandWords, Scanner in) throws CommandParseException{
		Command comand = null;
		int i = 0;
		
		while(i < availableCommands.length && comand == null) {
			comand = availableCommands[i].parse(commandWords,in);
			i++;
		}
		return comand;
	}
	
	// Returns All the information about the available commands
	public static String commandHelp() {
		String text = "\nThe available commands are:\n" 
					+ "----------------------------\n";
		
		for(int i = 0; i < availableCommands.length; i++)
			text += availableCommands[i].helpText();
	
		return text;
	}
}
