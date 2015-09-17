package dice.abstractDice;

import gui.Polygons;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Polygon;
import dice.enums.Face;

public abstract class EightSidedDie extends AbstractEotEDie {

	protected static final Integer NUMBER_OF_FACES = 8;

	@Override
	public final Integer getNumberOfFaces() {
		return NUMBER_OF_FACES;
	}

	protected static List<Face> createEightSidedDie(Face face1, Face face2, Face face3, Face face4, Face face5, Face face6, Face face7,
													Face face8) {
		List<Face> faces = new ArrayList<>();
		faces.add(face1);
		faces.add(face2);
		faces.add(face3);
		faces.add(face4);
		faces.add(face5);
		faces.add(face6);
		faces.add(face7);
		faces.add(face8);

		return faces;

	}

	@Override
	public Polygon getShape() {
		Polygon dieShape = Polygons.createDiamond(3.0);
		dieShape.setFill(getColor());
		return dieShape;
	}
}
