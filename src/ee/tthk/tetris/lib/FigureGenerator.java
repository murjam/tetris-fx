package ee.tthk.tetris.lib;

import ee.tthk.tetris.parts.Block;
import ee.tthk.tetris.parts.Figure;
import javafx.scene.paint.Color;

public class FigureGenerator {
	
	private double randomTone() {
		return Math.random() * .5 + .2;
	}
	
	private Color randomColor() {
		return new Color(randomTone(), randomTone(), randomTone(), 1);
	}
	
	public Figure createFigure() {

		Figure figure = new Figure();
		
		Block block = new Block(0, 0);
		Color color = randomColor();
		block.setFill(color);
		
		while (figure.size() < 4) {
			
			if (!CollisionDetector.collide(figure, block)) {
				figure.add(block);
				block = new Block(block.getX() / Block.SIZE,
						block.getY() / Block.SIZE);
				block.setFill(color);
			}
			
			int random = (int) (Math.random() * 4);
			switch (random) {
			case 1:
				block.setX(block.getX() - Block.SIZE);
				break;
			case 2:
				block.setX(block.getX() + Block.SIZE);
				break;
			case 3:
				block.setY(block.getY() - Block.SIZE);
				break;
			default:
				block.setY(block.getY() + Block.SIZE);
				break;
			}
		}
		
		return figure;
	}

}
