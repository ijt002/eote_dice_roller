package dice.enums;

public enum Symbol {

	SUCCESS("s"),
	ADVANTAGE("a"),
	TRIUMPH("x"),
	FAILURE("f"),
	THREAT("t"),
	DESPAIR("y"),
	LIGHT_FORCE("Z"),
	DARK_FORCE("z");

	private final String character;

	private Symbol(String character) {
		this.character = character;
	}

	public String getCharacter() {
		return character;
	}

}
