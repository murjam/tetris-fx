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
	
	public void rotateClockwise() {
		
		ObservableList<Node> children = getChildren();
		
		for (Node child : children) {
			if (child instanceof Block) {
				Block block = (Block) child;
				double x = block.getX();
				block.setX(-block.getY());
				block.setY(x);
			}
		}
	}
	
	/**
	 * Returns the list of blocks on their absolute positions.
	 */
	public List<Block> breakUp() {
		
		double figureX = getLayoutX();
		double figureY = getLayoutY();
		
		List<Block> blocks = new ArrayList<Block>();
		
		ObservableList<Node> children = getChildren();
		for (Node child : children) {
			if (child instanceof Block) {
				Block block = (Block) child;
				block.setX(block.getX() + figureX);
				block.setY(block.getY() + figureY);
				blocks.add(block);
			}
		}
		
		return blocks;
	}
	
}
