package dice.abstractDice;

import gui.Polygons;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Polygon;
import dice.enums.Face;

public abstract class SixSidedDie extends AbstractEotEDie {

	protected static final Integer NUMBER_OF_FACES = 6;

	@Override
	public final Integer getNumberOfFaces() {
		return NUMBER_OF_FACES;
	}

	protected static List<Face> createSixSidedDie(Face face1, Face face2, Face face3, Face face4, Face face5, Face face6) {

		List<Face> faces = new ArrayList<>();
		faces.add(face1);
		faces.add(face2);
		faces.add(face3);
		faces.add(face4);
		faces.add(face5);
		faces.add(face6);

		return faces;

	}

	@Override
	public Polygon getShape() {
		Polygon dieShape = Polygons.createSquare(4.0);
		dieShape.setFill(getColor());
		return dieShape;
	}

}
