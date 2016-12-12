package ee.itcollege.tetris.parts;

import static org.junit.Assert.*;

import org.junit.Test;

public class FigureTest {
	
	public static final double DELTA = .00001;

	
	@Test
	public void testInit() {
		
		Figure figure = new Figure();
		Block block = new Block(0, 0);
		figure.getChildren().add(block);
		
		assertEquals(0, figure.getLayoutX(), DELTA);
		assertEquals(0, figure.getLayoutY(), DELTA);
	}
	
	
	@Test
	public void testMove() {
		Figure figure = new Figure();
		Block block = new Block(0, 0);
		figure.getChildren().add(block);
		figure.move(1, 0);
		assertEquals(Block.SIZE, figure.getLayoutX(), DELTA);
		assertEquals(0, figure.getLayoutY(), DELTA);
		
		figure.move(0, 2);
		assertEquals(Block.SIZE, figure.getLayoutX(), DELTA);
		assertEquals(Block.SIZE * 2, figure.getLayoutY(), DELTA);
		
		figure.move(-3, 0);
		assertEquals(Block.SIZE * -2, figure.getLayoutX(), DELTA);
		assertEquals(Block.SIZE *  2, figure.getLayoutY(), DELTA);
		
		figure.move(0, -5);
		assertEquals(Block.SIZE * -2, figure.getLayoutX(), DELTA);
		assertEquals(Block.SIZE * -3, figure.getLayoutY(), DELTA);
		
		figure.move(6, 8);
		assertEquals(Block.SIZE * 4, figure.getLayoutX(), DELTA);
		assertEquals(Block.SIZE * 5, figure.getLayoutY(), DELTA);
	}
	
	@Test
	public void testRotate() {
		Figure figure = new Figure();
		Block block = new Block(1, 2);
		figure.getChildren().add(block);
		
		assertEquals(Block.SIZE * 1, block.getX(), DELTA);
		assertEquals(Block.SIZE * 2, block.getY(), DELTA);
		
		figure.rotate();
		assertEquals(Block.SIZE * -2, block.getX(), DELTA);
		assertEquals(Block.SIZE *  1, block.getY(), DELTA);
		
		figure.rotate();
		assertEquals(Block.SIZE * -1, block.getX(), DELTA);
		assertEquals(Block.SIZE * -2, block.getY(), DELTA);
		
		figure.rotate();
		assertEquals(Block.SIZE *  2, block.getX(), DELTA);
		assertEquals(Block.SIZE * -1, block.getY(), DELTA);
		
	}

}
