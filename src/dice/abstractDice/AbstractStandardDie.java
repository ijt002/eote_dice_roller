package dice.abstractDice;

import javafx.scene.paint.Color;

public abstract class AbstractStandardDie extends AbstractDie {

	protected Integer result;

	public abstract Color getTextColor();

	@Override
	public void roll() {
		result = getRawRoll();
	}

	public Integer getResult() {
		return result;
	}
}
