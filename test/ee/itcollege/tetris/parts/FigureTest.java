package ee.itcollege.tetris.parts;

import static org.junit.Assert.*;

import org.junit.Test;

public class FigureTest {

	private static final double DELTA = .0001;

	@Test
	public void testMove() {
		Figure figure = new Figure();
		Block block = new Block(0, 0);
		figure.add(block);
		
		assertEquals(0, block.getX(), DELTA);
		assertEquals(0, block.getY(), DELTA);
		
		figure.move(1, 0);
		assertEquals(1 * Block.SIZE, block.getX(), DELTA);
		assertEquals(0, block.getY(), DELTA);
		
		figure.move(0, 2);
		assertEquals(1 * Block.SIZE, block.getX(), DELTA);
		assertEquals(2 * Block.SIZE, block.getY(), DELTA);
		
		figure.move(-2, -5);
		assertEquals(-1 * Block.SIZE, block.getX(), DELTA);
		assertEquals(-3 * Block.SIZE, block.getY(), DELTA);
		
	}
	
	@Test
	public void testRotate() {
		Figure figure = new Figure();
		figure.add(new Block(0, 0)); // must be center block
		
		Block block = new Block(2, 3);
		figure.add(block);
		
		assertEquals( 2 * Block.SIZE, block.getX(), DELTA);
		assertEquals( 3 * Block.SIZE, block.getY(), DELTA);
		
		figure.rotate();
		assertEquals(-3 * Block.SIZE, block.getX(), DELTA);
		assertEquals( 2 * Block.SIZE, block.getY(), DELTA);
			
		figure.rotate();
		assertEquals(-2 * Block.SIZE, block.getX(), DELTA);
		assertEquals(-3 * Block.SIZE, block.getY(), DELTA);
		
		figure.rotate();
		assertEquals( 3 * Block.SIZE, block.getX(), DELTA);
		assertEquals(-2 * Block.SIZE, block.getY(), DELTA);
		
		figure.rotate();
		assertEquals( 2 * Block.SIZE, block.getX(), DELTA);
		assertEquals( 3 * Block.SIZE, block.getY(), DELTA);
	}

}












