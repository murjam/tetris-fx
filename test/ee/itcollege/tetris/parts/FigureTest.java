package ee.itcollege.tetris.parts;

import static org.junit.Assert.*;

import org.junit.Test;

public class FigureTest {
	
	public static final double PRECISION = .000001; 

	@Test
	public void testRotate() {
		Figure figure = new Figure();
		Block block = new Block(3, -1);
		figure.getChildren().add(block);
		
		figure.rotate();
		assertEquals(Block.SIZE * 1, block.getX(), PRECISION);
		assertEquals(Block.SIZE * 3, block.getY(), PRECISION);

		figure.rotate();
		assertEquals(Block.SIZE * -3, block.getX(), PRECISION);
		assertEquals(Block.SIZE * 1, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals(Block.SIZE * -1, block.getX(), PRECISION);
		assertEquals(Block.SIZE * -3, block.getY(), PRECISION);
		
		figure.rotate();
		assertEquals(Block.SIZE * 3, block.getX(), PRECISION);
		assertEquals(Block.SIZE * -1, block.getY(), PRECISION);
	}
	
	
	@Test(timeout=1000)
	public void testMovePositive() {

		Figure figure = new Figure();
		figure.setLayoutX(0);
		figure.setLayoutY(0);
		
		assertEquals(0, figure.getLayoutX(), PRECISION);
		assertEquals(0, figure.getLayoutY(), PRECISION);
		
		figure.move(1, 0);
		assertEquals(Block.SIZE * 1, figure.getLayoutX(), PRECISION);
		assertEquals(0, figure.getLayoutY(), PRECISION);
		
		figure.move(0, 1);
		assertEquals(Block.SIZE * 1, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE * 1, figure.getLayoutY(), PRECISION);
		
		figure.move(2, 1);
		assertEquals(Block.SIZE * 3, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE * 2, figure.getLayoutY(), PRECISION);
		
	}
	
	@Test(timeout=1000)
	public void testMoveNegative() {
		
		Figure figure = new Figure();
		figure.setLayoutX(0);
		figure.setLayoutY(0);
		
		figure.move(0, -1);
		assertEquals(Block.SIZE * 0, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE * -1, figure.getLayoutY(), PRECISION);
		
		figure.move(-1, 0);
		assertEquals(Block.SIZE * -1, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE * -1, figure.getLayoutY(), PRECISION);
		
		figure.move(2, -1);
		assertEquals(Block.SIZE * 1, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE * -2, figure.getLayoutY(), PRECISION);
	}

}
