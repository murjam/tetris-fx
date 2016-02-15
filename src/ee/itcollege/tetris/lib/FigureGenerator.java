package ee.itcollege.tetris.lib;

import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;

public class FigureGenerator {
	
	public Figure createFigure() {

		// TODO create different figures, choose randomly
		
		Figure figure = new Figure();
		
		figure.getChildren().add(new Block(-1, 0));
		figure.getChildren().add(new Block(0, 0));
		figure.getChildren().add(new Block(1, 0));
		figure.getChildren().add(new Block(0, 1));
		
		return figure;
	}

}
