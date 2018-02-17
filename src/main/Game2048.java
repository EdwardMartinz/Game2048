package main;

import java.util.Random;
import java.util.Scanner;

import control.Controller;
import logic.Game;
import logic.GameType;


public class Game2048 {

	public static void main(String[] args) {
	
			// Seed for the random behavior
			long seed = new Random().nextInt(1000);
			
<<<<<<< HEAD
			// Creates game
			Game game = new Game(GameType.ORIG, Game.SIZE, Game.INIT_CELLS, seed);
=======
			// Creates a game
			Game game = new Game(GameType.ORIG, 4, 2, seed);
>>>>>>> df03e4fbb0dfeedd833e1a52ea1e84382c7b300a
			
			// Creates a Controller
			Scanner in = new Scanner(System.in);
			Controller controlador = new Controller(game, in);
			
			// Runs the game
			controlador.run();
			
	}
}
