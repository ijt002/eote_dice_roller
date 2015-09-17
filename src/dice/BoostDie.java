package dice;

import javafx.scene.paint.Color;
import dice.abstractDice.SixSidedDie;
import dice.enums.Face;

public class BoostDie extends SixSidedDie {

	public BoostDie() {

		faces = createSixSidedDie(Face.EMPTY, Face.EMPTY, Face.SUCCESS, Face.SUCCESS_ADVANTAGE, Face.DOUBLE_ADVANTAGE, Face.ADVANTAGE);
	}

	@Override
	public Color getColor() {
		return Color.LIGHTBLUE;
	}

	@Override
	public Boolean isPositive() {
		return true;
	}
}
