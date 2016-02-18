package ee.itcollege.tetris.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

	@Test
	public void testScore() {
		
		Score score = new Score("Mati", 12);
		assertEquals("Mati", score.getPlayerName());
		assertEquals(12, score.getScore());
		
		try {
			score = new Score("", 15);
			fail("Score should not allow empty strings for names");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Mati", score.getPlayerName());	
		}
		
		try {
			score = new Score("    \n  \t  ", 16);
			fail("Score should not allow empty strings for names");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Mati", score.getPlayerName());	
		}
		
		
		try {
			score = new Score(null, -12);
			fail("Score should not allow nulls as names");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Mati", score.getPlayerName());	
		}
		
		try {
			score = new Score("Tooma", -12);
			fail("Score should not allow negative scores");
		}
		catch (IllegalArgumentException e) {
			assertEquals(12, score.getScore());	
		}
		
	}
	@Test
	public void testTrim() {
		Score score = new Score("   M  a  ti  ", 12);
		assertEquals("M  a  ti", score.getPlayerName());
		assertEquals(12, score.getScore());
	}

}
