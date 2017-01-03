package ee.tthk.tetris.parts;

import java.util.ArrayList;

import ee.tthk.tetris.lib.CollisionDetector;
import javafx.scene.shape.Shape;

@SuppressWarnings("serial")
public class Figure extends ArrayList<Block> {
	
	private Block getCenterBlock() {
		if (size() > 1) {
			return get(1);
		}
		return get(0);
	}

	public void move(int deltaX, int deltaY) {
		
		for (Block block : this) {
			block.setX(block.getX() + deltaX * Block.SIZE);
			block.setY(block.getY() + deltaY * Block.SIZE);
		}
		
	}
	
	public boolean moveIfPossible(int x, int y, ArrayList<Shape> gameField) {
		move(x, y);
		if (CollisionDetector.collide(this, gameField)) {
			move(-x, -y);
			return false;
		}
		return true;
	}

	public void rotate() {
		Block center = getCenterBlock();
		
		for (Block block : this) {
			double x = block.getX() - center.getX();
			double y = block.getY() - center.getY();
			block.setX(-y + center.getX());
			block.setY(x + center.getY());
		}
	}
	
}





