package gui;

import javafx.scene.shape.Polygon;

public class Polygons {

	public static Polygon createPentagon(Double scale) {
		Double[] coords = new Double[] { 0.0, -10.0, -9.5, -3.1, -5.9, 8.1, 5.9, 8.1, 9.5, -3.1 };
		Polygon polygon = createPolygon(scale, coords);
		return polygon;
	}

	public static Polygon createHexagon(Double scale) {
		Double[] coords = new Double[] { 10.0, 0.0, 5.0, -8.7, -5.0, -8.7, -10.0, 0.0, -5.0, 8.7, 5.0, 8.7 };
		Polygon polygon = createPolygon(scale, coords);
		return polygon;
	}

	public static Polygon createSquare(Double scale) {
		Double[] coords = new Double[] { 7.1, -7.1, -7.1, -7.1, -7.1, 7.1, 7.1, 7.1 };
		Polygon polygon = createPolygon(scale, coords);
		return polygon;
	}

	public static Polygon createDiamond(Double scale) {
		Double[] coords = new Double[] { 10.0, 0.0, 0.0, -13.0, -10.0, 0.0, 0.0, 13.0 };
		Polygon polygon = createPolygon(scale, coords);
		return polygon;
	}

	public static Polygon createPolygon(Double scale, Double[] coords) {
		for (int i = 0; i < coords.length; i++) {
			coords[i] *= scale;
		}
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(coords);
		polygon.getStyleClass().add("chance-polygon");
		return polygon;
	}

}
