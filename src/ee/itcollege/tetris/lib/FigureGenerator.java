package ee.itcollege.tetris.lib;


import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.BlockGroup;
import javafx.scene.paint.Color;

public class FigureGenerator {
	
	public BlockGroup createFigure() {

		// TODO create different figures, choose randomly
		
		BlockGroup figure = new BlockGroup();
		
		int x = 0;
		int y = 0;
		int lastX = x;
		int lastY = y;
		
		Color color = Color.RED;
		
		
		while (figure.size() < 4) {
			boolean changeX = 0 == (int)(Math.random() * 2);
			int change = 0 == (int)(Math.random() * 2) ? 1 : -1;
			
			if (changeX) {
				x += change;
			}
			else {
				y += change;
			}
			Block block = new Block(x, y);
			
			if (CollisionDetector.collide(figure, block)) {
				x = lastX;
				y = lastY;
				continue;
			}
			lastX = x;
			lastY = y;
			block.setFill(color);
			figure.add(block);
			if (figure.size() == 2) {
				figure.setCenterBlock(block);
			}
		}
		
		
		return figure;
	}

}
