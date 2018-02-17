package commands;

import java.util.Random;
import java.util.Scanner;

import exceptions.CommandParseException;
import logic.Game;
import logic.GameType;

public class PlayCommand extends Command {

	// Play parse Exceptions
	public static final String INVALID_GAME = "Invalid game type for play command\n";
	public static final String PLAY_NO_PARAM = "Play must be followed by a game type: " 
												+ GameType.externaliseAll() + "\n";

	// Atributes
	private GameType gameType;
	private int pSize;
	private int pInitCells;
	private long pSeed;

	private final static String COMMAND_INFO = "play <game>";
	private final static String HELP_INFO = "start a new game of one of the game types: "
											 + GameType.externaliseAll() + "\n";

	// Constructors
	public PlayCommand() {
		super(COMMAND_INFO, HELP_INFO);
	}

	public PlayCommand(GameType gameType, int size, int initCells, long seed) {
		super(COMMAND_INFO, HELP_INFO);
		this.gameType = gameType;
		pSize = size;
		pInitCells = initCells;
		pSeed = seed;
	}

	@Override
	// Execute
	public boolean execute(Game game) {
		game.changeTheGame(gameType, pSize, pInitCells, pSeed);
		return true;
	}

	// Parse
	@Override
	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		boolean correctEntry;

		if (commandWords.length == 2 && commandWords[0].equals(commandName)) {

			// Validate Gametype
			
			GameType newGameType = GameType.parse(commandWords[1]);

			if (newGameType != null) {
				System.out.println("*** You have chosen to play the game: " 
									+ newGameType.getDescription() + " ***");

				// Asks for the size of the board
				
				do {
					correctEntry = true;
					
					try {

						System.out.print("\nPlease enter the size of the board : ");
						String sizeS = in.nextLine().trim();

						if (sizeS.isEmpty()) {
							pSize = Game.SIZE;
							System.out.println("--> Using the default size of the board: " 
													+ Game.SIZE + "\n");

						} else {
							pSize = Integer.parseInt(sizeS);
						}

					} catch (NumberFormatException nfe) {
						System.out.println("The size of the board must be a number !!");
						correctEntry = false;
					}

				} while (!correctEntry);
				

				// Asks for the number of initial cells
				
				do {
					correctEntry = true;

					try {
						
						System.out.print("\nPlease enter the number of initial cells : ");
						String initS = in.nextLine().trim();

						if (initS.isEmpty()) {
							pInitCells = Game.INIT_CELLS;
							System.out.println("--> Using the default number of initial cells: "
													+ Game.INIT_CELLS + "\n");

						} else { pInitCells = Integer.parseInt(initS); }

					} catch (NumberFormatException nfe) {
						System.out.println("The number of initial cells must be a number !!");
						correctEntry = false;
					}

				} while (!correctEntry);

				// Asks for the seed to generate the random number
				
				do {
					correctEntry = true;
					
					try {
						
						System.out.print("\nPlease enter the seed for the "
										+ "pseudo-random number generator:");
						String randomS = in.nextLine().trim();

						if (randomS.isEmpty()) {
							pSeed = new Random().nextInt(1000);
							System.out.println("--> Using the default seed for the"
									+ " pseudo-random number generator: " + pSeed);

						} else { pSeed = Integer.parseInt(randomS); }

					} catch (NumberFormatException nfe) {
						System.out.println("The seed for the pseudo-random number "
										 + "generator must be a number !!");
						
						correctEntry = false;
					}

				} while (!correctEntry);

				// Returns a Play Command
				
				return new PlayCommand(newGameType, pSize, pInitCells, pSeed);
				
			} else {
				throw new CommandParseException(INVALID_GAME);
			}
			
		} else if (commandWords.length != 2 && commandWords[0].equals(commandName)) {

			throw new CommandParseException(PLAY_NO_PARAM);

		} else { return null; }
		
	}
	
}
