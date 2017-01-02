package ee.itcollege.tetris.lib;

import static org.junit.Assert.*;


import org.junit.Test;
import javafx.scene.Group;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.BlockGroup;

public class CollisionDetectorTest {

	@Test
	public void testCollides() {
		
		Group parent = new Group();
		
		Block block = new Block(1, 1);
		
		BlockGroup figure = new BlockGroup();
		figure.addBlock(new Block(0, 0));
		
		parent.getChildren().addAll(figure.getBlocks());
		parent.getChildren().add(block);
		
		assertFalse(CollisionDetector.collide(figure, block));
		
		figure.move(1, 0);
		assertFalse(CollisionDetector.collide(figure, block));
		
		figure.move(0, 1);
		assertTrue(CollisionDetector.collide(figure, block));
		
		figure.move(0, 1);
		assertFalse(CollisionDetector.collide(figure, block));
	}

}
