package ee.itcollege.tetris.parts;

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
		
		for (Node node : children) {
			if (node instanceof Block) {
				Block block = (Block) node;
				double x = block.getX();
				block.setX(-block.getY());
				block.setY(x);
			}
		}
		
	}
	
	
}
