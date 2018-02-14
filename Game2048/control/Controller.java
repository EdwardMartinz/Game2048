package control;

import java.util.Scanner;

import commands.Command;
import exceptions.CommandParseException;
import exceptions.ExecuteException;
import exceptions.GameOverException;
import logic.Game;
import utils.CommandParser;

public class Controller {

	private Game game;
	private Scanner in;

	// Constructor
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}

	// Runs the game
	public void run() {
		String command;
		boolean printGame = true;

		while (!game.isFinished()) {

			if (printGame)
				System.out.println(game);
			
			// Saves the command
			System.out.print("\nCommand > ");
			command = in.nextLine().trim().toLowerCase();
			String[] commandWords = command.split("\\s+");
			
			try {
				// Looks for an available command
				Command comando = CommandParser.parseCommand(commandWords, in);
				
				// Command found
				if (comando != null){
					printGame = comando.execute(game);
					
				// Command not found
				}else { 
					System.out.println("--> Unknown command !!\n" 
					+ "Use 'help' to see the available commands");
				}
			
			// Exceptions	
			}catch(ExecuteException ex) {
				printGame = false;
				System.out.println(ex.getMessage());
				
			}catch(CommandParseException cpe) {
				printGame = false;
				System.out.println(cpe.getMessage());
				
			}catch(GameOverException goe) {
				System.out.println(game); 
				System.out.println(goe.getMessage());
				
			} 
		}
		
		System.out.println("Game Over..."); 
	}	
}
