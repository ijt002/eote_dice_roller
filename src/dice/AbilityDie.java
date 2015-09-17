package dice;

import javafx.scene.paint.Color;
import dice.abstractDice.EightSidedDie;
import dice.enums.Face;

public class AbilityDie extends EightSidedDie {

	public AbilityDie() {

		faces = createEightSidedDie(Face.EMPTY, Face.SUCCESS, Face.SUCCESS, Face.DOUBLE_SUCCESS, Face.ADVANTAGE, Face.ADVANTAGE,
									Face.SUCCESS_ADVANTAGE, Face.DOUBLE_ADVANTAGE);
	}

	@Override
	public Color getColor() {
		return Color.web("0x66AA00");
	}

	@Override
	public Boolean isPositive() {
		return true;
	}
}
