package dice;

import javafx.scene.paint.Color;
import dice.abstractDice.TwelveSidedDie;
import dice.enums.Face;

public class ProficiencyDie extends TwelveSidedDie {

	public ProficiencyDie() {

		faces = createTwelveSidedDie(	Face.EMPTY, Face.SUCCESS, Face.SUCCESS, Face.DOUBLE_SUCCESS, Face.DOUBLE_SUCCESS, Face.ADVANTAGE,
										Face.SUCCESS_ADVANTAGE, Face.SUCCESS_ADVANTAGE, Face.SUCCESS_ADVANTAGE, Face.DOUBLE_ADVANTAGE,
										Face.DOUBLE_ADVANTAGE, Face.TRIUMPH);
	}

	@Override
	public Color getColor() {
		return Color.YELLOW;
	}

	@Override
	public Boolean isPositive() {
		return true;
	}
}
