package dice.enums;

import java.util.ArrayList;
import java.util.List;

public enum Face {

	EMPTY(),
	SUCCESS(Symbol.SUCCESS),
	ADVANTAGE(Symbol.ADVANTAGE),
	DOUBLE_ADVANTAGE(Symbol.ADVANTAGE, Symbol.ADVANTAGE),
	DOUBLE_SUCCESS(Symbol.SUCCESS, Symbol.SUCCESS),
	SUCCESS_ADVANTAGE(Symbol.SUCCESS, Symbol.ADVANTAGE),
	FAILURE(Symbol.FAILURE),
	DOUBLE_FAILURE(Symbol.FAILURE, Symbol.FAILURE),
	THREAT(Symbol.THREAT),
	DOUBLE_THREAT(Symbol.THREAT, Symbol.THREAT),
	FAILURE_THREAT(Symbol.FAILURE, Symbol.THREAT),
	TRIUMPH(Symbol.TRIUMPH),
	DESPAIR(Symbol.DESPAIR),
	DARK_FORCE(Symbol.DARK_FORCE),
	DOUBLE_DARK_FORCE(Symbol.DARK_FORCE, Symbol.DARK_FORCE),
	LIGHT_FORCE(Symbol.LIGHT_FORCE),
	DOUBLE_LIGHT_FORCE(Symbol.LIGHT_FORCE, Symbol.LIGHT_FORCE);

	private final List<Symbol> symbols;

	private Face(List<Symbol> symbols) {
		this.symbols = symbols;
	}

	private Face() {
		this.symbols = new ArrayList<>();
	}

	private Face(Symbol symbol) {
		List<Symbol> symbols1 = new ArrayList<>();
		symbols1.add(symbol);
		this.symbols = symbols1;
	}

	private Face(Symbol symbol, Symbol symbol2) {
		List<Symbol> symbols1 = new ArrayList<>();
		symbols1.add(symbol);
		symbols1.add(symbol2);
		this.symbols = symbols1;
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

}
