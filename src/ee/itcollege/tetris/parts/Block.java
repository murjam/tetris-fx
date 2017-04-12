package ee.itcollege.tetris.parts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle implements Comparable<Block> {

	public static final int SIZE = 30;

	public Block(double x, double y) {
		super(x * SIZE, y * SIZE, SIZE - 1, SIZE - 1);

		setFill(Color.BLACK);
	}

	@Override
	public int compareTo(Block other) {
		int row = Double.compare(getY(), other.getY());
		if (row != 0) {
			return row;
		}
		return Double.compare(getX(), other.getX());
	}

}
