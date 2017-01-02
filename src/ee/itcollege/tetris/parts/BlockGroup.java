package ee.itcollege.tetris.parts;

import java.util.ArrayList;
import java.util.function.Consumer;

import ee.itcollege.tetris.lib.CollisionDetector;

@SuppressWarnings("serial")
public class BlockGroup extends ArrayList<Block> {
	
	private Block centerBlock;
	
	public void setCenterBlock(Block centerBlock) {
		this.centerBlock = centerBlock;
	}
	
	public Block getCenterBlock() {
		if (null == centerBlock) {
			return get(0);
		}
		return centerBlock;
	}
	
	public void eachBlock(Consumer<Block> consumer) {
		for (Block block : this) {
			consumer.accept(block);
		}
	}

	public void move(int deltaX, int deltaY) {
		eachBlock(block -> {
			block.setX(block.getX() + deltaX * Block.SIZE);
			block.setY(block.getY() + deltaY * Block.SIZE);
		});
	}
	
	public void rotateClockwise() {
		double centerY = getCenterBlock().getY();
		double centerX = getCenterBlock().getX();
		eachBlock(block -> {
			
			double x = block.getX() - centerX;
			double y = block.getY() - centerY;
			block.setX(-y + centerX);
			block.setY(x + centerY);
		});
	}

	public boolean moveIfPossible(int deltaX, int deltaY, BlockGroup fallenBlocks) {
		move(deltaX, deltaY);
		if (CollisionDetector.collide(this, fallenBlocks)) {
			move(-deltaX, -deltaY);
			return false;
		}
		return true;
	}

	
}
