package dice;

import javafx.scene.paint.Color;
import dice.abstractDice.TwelveSidedDie;
import dice.enums.Face;

public class ChallengeDie extends TwelveSidedDie {

	public ChallengeDie() {
		faces = createTwelveSidedDie(	Face.EMPTY, Face.FAILURE, Face.FAILURE, Face.DOUBLE_FAILURE, Face.DOUBLE_FAILURE, Face.THREAT,
										Face.THREAT, Face.FAILURE_THREAT, Face.FAILURE_THREAT, Face.DOUBLE_THREAT, Face.DOUBLE_THREAT,
										Face.DESPAIR);
	}

	@Override
	public Color getColor() {
		return Color.DARKRED;
	}

	@Override
	public Boolean isPositive() {
		return false;
	}
}
