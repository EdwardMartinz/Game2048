package logic;

public enum GameType {

	ORIG("original", "2048, Original version", new GameRules2048()),  
	FIB("fib", "2048, Fibonacci version", new GameRulesFibonacci()),  
	INVERSE("inverse","2048, Inverse version", new GameRulesInverse());
	
	private String gameParameter;
	private String gameDescription;
	private GameRules correspondingRules;
	
	private GameType(String parameter, String description, GameRules rules) {
		gameParameter = parameter;
		gameDescription = description;
		correspondingRules = rules;
	}
	
	// Tests if a given string matches any Enum
	public static GameType parse(String test) {
		for (GameType gametype : GameType.values()) {
			if (gametype.gameParameter.equals(test)) {
				return gametype;
			}
		}
		return null;
	}
	
	// Shows a message with the name of all the games
	public static String externaliseAll () {
		String s = "";
		
		for (GameType type : GameType.values())
			s = s + " " + type.gameParameter + ",";
		
		return s.substring(1,s.length() - 1);
	}

	public String externalise() {
		return gameParameter;
	}

	public String getDescription() {
		return gameDescription;
	}
	
	public GameRules getRules() {
		return correspondingRules;
	}
}
