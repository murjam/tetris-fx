package ee.itcollege.tetris.parts;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

public class Figure extends Group {

	public void move(int deltaX, int deltaY) {
		setLayoutX(getLayoutX() + deltaX * Block.SIZE);
		setLayoutY(getLayoutY() + deltaY * Block.SIZE);
	}
	
	public List<Block> getBlocks() {
		ObservableList<Node> children = getChildren();
		List<Block> blocks = new ArrayList<Block>();
		for (Node node : children) {
			if (node instanceof Block) {
				blocks.add((Block) node);
			}
		}
		return blocks;
	}

	/**
	 * Rotates the figure clockwise by changing the
	 * x-s and y-s of blocks
	 */
	public void rotate() {
		List<Block> blocks = getBlocks();
		
		for (Block block : blocks) {
			double rememberX = block.getX();
			block.setX(block.getY() * -1);
			block.setY(rememberX);
		}
	}
	
}
