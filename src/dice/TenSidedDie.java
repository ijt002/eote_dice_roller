package dice;

import gui.Polygons;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import dice.abstractDice.AbstractStandardDie;

public class TenSidedDie extends AbstractStandardDie {

	private static final Random RANDOM_COLOUR_GENERATOR = new Random();

	protected static final Integer NUMBER_OF_FACES = 10;

	private final Color dieColor;

	private final Color textColor;

	public TenSidedDie() {
		dieColor = new Color(RANDOM_COLOUR_GENERATOR.nextDouble(), RANDOM_COLOUR_GENERATOR.nextDouble(),
				RANDOM_COLOUR_GENERATOR.nextDouble(), 1);
		textColor = new Color(1 - dieColor.getRed(), 1 - dieColor.getGreen(), 1 - dieColor.getBlue(), 1);
	}

	@Override
	public Integer getNumberOfFaces() {
		return NUMBER_OF_FACES;
	}

	@Override
	public Color getColor() {
		return dieColor;
	}

	@Override
	public Color getTextColor() {
		return textColor;
	}

	@Override
	public Polygon getShape() {
		Polygon dieShape = Polygons.createPentagon(3.4);
		dieShape.setFill(getColor());
		return dieShape;
	}

}
