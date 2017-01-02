package ee.itcollege.tetris.parts;

import java.util.ArrayList;
import java.util.function.Consumer;

import ee.itcollege.tetris.lib.CollisionDetector;

public class BlockGroup {
	
	private Block centerBlock;
	
	private ArrayList<Block> blocks = new ArrayList<Block>();
	
	public void addBlock(Block block) {
		if (blocks.isEmpty()) {
			centerBlock = block;
		}
		blocks.add(block);
	}
	
	public void setCenterBlock(Block centerBlock) {
		this.centerBlock = centerBlock;
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public void eachBlock(Consumer<Block> consumer) {
		for (Block block : blocks) {
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
		double centerY = centerBlock.getY();
		double centerX = centerBlock.getX();
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
