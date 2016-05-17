package ee.itcollege.tetris.lib;

import javafx.scene.Node;

public class CollisionDetector {

	public static boolean collide(Node n1, Node n2) {
		return n1.getBoundsInParent().intersects(n2.getBoundsInParent());
	}
	
}
