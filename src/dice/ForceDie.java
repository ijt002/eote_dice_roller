package dice;

import javafx.scene.paint.Color;
import dice.abstractDice.TwelveSidedDie;
import dice.enums.Face;

public class ForceDie extends TwelveSidedDie {

	public ForceDie() {
		faces = createTwelveSidedDie(	Face.DARK_FORCE, Face.DARK_FORCE, Face.DARK_FORCE, Face.DARK_FORCE, Face.DARK_FORCE,
										Face.DARK_FORCE,
										Face.DOUBLE_DARK_FORCE, Face.LIGHT_FORCE, Face.LIGHT_FORCE, Face.DOUBLE_LIGHT_FORCE,
										Face.DOUBLE_LIGHT_FORCE, Face.DOUBLE_LIGHT_FORCE);
	}

	@Override
	public Color getColor() {
		return Color.WHITE;
	}

	@Override
	public Boolean isPositive() {
		return true;
	}
}
