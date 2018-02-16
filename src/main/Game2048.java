package main;

import java.util.Random;
import java.util.Scanner;

import control.Controller;
import logic.Game;
import logic.GameType;


public class Game2048 {

	public static void main(String[] args) {
	
		if(args.length == 2 || args.length == 3) {
			
			// 2 first parameters
			int dimension = Integer.parseInt(args[0]);
			int initCells = Integer.parseInt(args[1]);
			
			// Seed
			long seed ;
			if(args.length == 2)
				seed = new Random().nextInt(1000);
			else
				seed = Long.parseLong(args[2]); 
			
			// Creates game
			Game game = new Game(GameType.ORIG, dimension, initCells, seed);
			
			// Creates a Controller
			Scanner in = new Scanner(System.in);
			Controller controlador = new Controller(game, in);
			
			// Runs the game
			controlador.run();
			
		}else
			System.out.println("Usage: Game 2048 <size> <initCells> [<seed>]");
	}
	
}
