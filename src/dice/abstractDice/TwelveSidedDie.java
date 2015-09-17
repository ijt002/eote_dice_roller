package dice.abstractDice;

import gui.Polygons;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Polygon;
import dice.enums.Face;

public abstract class TwelveSidedDie extends AbstractEotEDie {

	protected static final Integer NUMBER_OF_FACES = 12;

	@Override
	public final Integer getNumberOfFaces() {
		return NUMBER_OF_FACES;
	}

	protected static List<Face> createTwelveSidedDie(Face face1, Face face2, Face face3, Face face4, Face face5, Face face6, Face face7,
														Face face8, Face face9, Face face10, Face face11, Face face12) {
		List<Face> faces = new ArrayList<>();
		faces.add(face1);
		faces.add(face2);
		faces.add(face3);
		faces.add(face4);
		faces.add(face5);
		faces.add(face6);
		faces.add(face7);
		faces.add(face8);
		faces.add(face9);
		faces.add(face10);
		faces.add(face11);
		faces.add(face12);

		return faces;
	}

	@Override
	public Polygon getShape() {
		Polygon dieShape = Polygons.createHexagon(3.3);
		dieShape.setFill(getColor());
		return dieShape;
	}

}
