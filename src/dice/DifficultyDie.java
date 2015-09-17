package dice;

import javafx.scene.paint.Color;
import dice.abstractDice.EightSidedDie;
import dice.enums.Face;

public class DifficultyDie extends EightSidedDie {

	public DifficultyDie() {

		faces = createEightSidedDie(Face.EMPTY, Face.FAILURE, Face.DOUBLE_FAILURE, Face.THREAT, Face.THREAT, Face.THREAT,
									Face.DOUBLE_THREAT, Face.FAILURE_THREAT);
	}

	@Override
	public Color getColor() {
		return Color.INDIGO;
	}

	@Override
	public Boolean isPositive() {
		return false;
	}
}
