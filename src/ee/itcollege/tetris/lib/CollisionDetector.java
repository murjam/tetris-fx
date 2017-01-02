package ee.itcollege.tetris.lib;


import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.BlockGroup;
import javafx.scene.shape.Shape;

public class CollisionDetector {

	public static boolean collide(Shape s1, Shape s2) {
		Shape intersect = Shape.intersect(s1, s2);
		return intersect.getBoundsInLocal().getWidth() != -1;
		//return n1.getBoundsInParent().intersects(n2.getBoundsInParent());
	}

	public static boolean collide(BlockGroup blocks, BlockGroup figure) {
		for (Block block : blocks.getBlocks()) {
			if (collide(figure, block)) {
				return true;
			}
		}
		return false;
	}

	public static boolean collide(BlockGroup figure, Block block) {
		for (Block b2 : figure.getBlocks()) {
			if (collide(block, b2)) {
				return true;
			}
		}
		return false;
	}

	
}
