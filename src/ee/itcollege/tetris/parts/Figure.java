package ee.itcollege.tetris.parts;

import javafx.scene.Group;
import javafx.scene.Node;

public class Figure extends Group {

	public void move(int deltaX, int deltaY) {
		for (Node block : this.getChildren()) {
			block.setLayoutY(block.getLayoutY() + deltaY * Block.SIZE);
		}
	}
	
}
