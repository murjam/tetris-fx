package ee.itcollege.tetris.parts;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class FigureTest {
	
	private static double PRECISION = 0.001;
	
	@Test
	public void testBreakUp() {
		Figure figure = new Figure();
		Block block = new Block(2, 1);
		figure.getChildren().add(block);
		
		figure.move(5, 10);
		assertEquals(5 * Block.SIZE, figure.getLayoutX(), PRECISION);
		assertEquals(10 * Block.SIZE, figure.getLayoutY(), PRECISION);
		
		List<Block> breakUp = figure.breakUp();
		Block first = breakUp.get(0);
		
		assertEquals(7 * Block.SIZE, first.getX(), PRECISION);
		assertEquals(11 * Block.SIZE, first.getY(), PRECISION);
	}
	
	@Test
	public void testRotate() {
		Figure figure = new Figure();
		Block block = new Block(2, 1);
		figure.getChildren().add(block);
		// test initial values
		assertEquals(2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(1 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotateClockwise();
		assertEquals(-1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals( 2 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotateClockwise();
		assertEquals(-2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(-1 * Block.SIZE, block.getY(), PRECISION);
		
		figure.rotateClockwise();
		assertEquals( 1 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(-2 * Block.SIZE, block.getY(), PRECISION);
		
		// double check 360 degrees
		figure.rotateClockwise();
		assertEquals(2 * Block.SIZE, block.getX(), PRECISION);
		assertEquals(1 * Block.SIZE, block.getY(), PRECISION);
	}
	

	@Test
	public void testMove1() {
		Figure figure = new Figure();
		// test for initial values
		assertEquals(0, figure.getLayoutX(), PRECISION);
		assertEquals(0, figure.getLayoutY(), PRECISION);
		
		// changing x
		figure.move(1, 0);
		assertEquals(Block.SIZE, figure.getLayoutX(), PRECISION);
		assertEquals(0, figure.getLayoutY(), PRECISION);
		
		// changing y
		figure.move(0, 1);
		assertEquals(Block.SIZE, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE, figure.getLayoutY(), PRECISION);
	}
	
	@Test
	public void testMove2() {
		Figure figure = new Figure();
		// testing for changing two values at once
		figure.move(1, 2);
		assertEquals(Block.SIZE, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE * 2, figure.getLayoutY(), PRECISION);
		
		// testing for negative values
		figure.move(-2, -1);
		assertEquals(-Block.SIZE, figure.getLayoutX(), PRECISION);
		assertEquals(Block.SIZE, figure.getLayoutY(), PRECISION);
	}
	

}
