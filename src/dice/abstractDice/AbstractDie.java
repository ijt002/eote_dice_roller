package dice.abstractDice;

import interfaces.Rollable;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class AbstractDie implements Rollable {

	private static final Random MASTER_ROLLER = new Random();

	protected final Integer getRawRoll() {
		return MASTER_ROLLER.nextInt(getNumberOfFaces());
	}

	public abstract Integer getNumberOfFaces();

	public abstract Color getColor();

	public abstract Polygon getShape();

}
