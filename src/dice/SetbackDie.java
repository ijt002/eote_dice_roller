package dice;

import javafx.scene.paint.Color;
import dice.abstractDice.SixSidedDie;
import dice.enums.Face;

public class SetbackDie extends SixSidedDie {

	public SetbackDie() {

		faces = createSixSidedDie(Face.EMPTY, Face.EMPTY, Face.FAILURE, Face.FAILURE, Face.THREAT, Face.THREAT);
	}

	@Override
	public Color getColor() {
		return Color.BLACK;
	}

	@Override
	public Boolean isPositive() {
		return false;
	}
}
