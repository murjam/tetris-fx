package ee.itcollege.tetris.parts;

import javafx.scene.Group;

public class Figure extends Group {

	public void move(int deltaX, int deltaY) {
		setLayoutX(getLayoutX() + deltaX * Block.SIZE);
	}
	
}
