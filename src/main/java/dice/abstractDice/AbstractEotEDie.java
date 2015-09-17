package dice.abstractDice;

import java.util.List;

import dice.enums.Face;

public abstract class AbstractEotEDie extends AbstractDie {

	protected List<Face> faces;

	protected Face result;

	@Override
	public final void roll() {
		Integer rawRoll = getRawRoll();
		result = faces.get(rawRoll);
	}

	public Face getResult() {
		return result;
	}

	public abstract Boolean isPositive();
}