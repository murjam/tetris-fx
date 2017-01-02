package ee.itcollege.tetris.parts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FigureTest {
	
	private static double PRECISION = 0.001;
	
	@Test
	public void testRotate() {
		BlockGroup figure = new BlockGroup();
		figure.add(new Block(0, 0));
		Block block = new Block(2, 1);
		figure.add(block);
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
		BlockGroup figure = new BlockGroup();
		Block block = new Block(0, 0);
		figure.add(block);
		// test for initial values
		assertEquals(0, block.getX(), PRECISION);
		assertEquals(0, block.getY(), PRECISION);
		
		// changing x
		figure.move(1, 0);
		assertEquals(Block.SIZE, block.getX(), PRECISION);
		assertEquals(0, block.getY(), PRECISION);
		
		// changing y
		figure.move(0, 1);
		assertEquals(Block.SIZE, block.getX(), PRECISION);
		assertEquals(Block.SIZE, block.getY(), PRECISION);
	}
	
	@Test
	public void testMove2() {
		BlockGroup figure = new BlockGroup();
		Block block = new Block(0, 0);
		figure.add(block);
		// testing for changing two values at once
		figure.move(1, 2);
		assertEquals(Block.SIZE, block.getX(), PRECISION);
		assertEquals(Block.SIZE * 2, block.getY(), PRECISION);
		
		// testing for negative values
		figure.move(-2, -1);
		assertEquals(-Block.SIZE, block.getX(), PRECISION);
		assertEquals(Block.SIZE, block.getY(), PRECISION);
	}
	

}
