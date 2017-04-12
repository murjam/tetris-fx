package ee.itcollege.tetris.lib;

import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;

public class FigureGenerator {

	public Figure createFigure() {

		Figure figure = new Figure();

		if (Math.random() < 0.5) {
			figure.add(new Block(0, 0));
			figure.add(new Block(-1, 0));
			figure.add(new Block(1, 0));
			figure.add(new Block(0, 1));
		}
		else {
			figure.add(new Block(0, 0));
			figure.add(new Block(0, 1));
			figure.add(new Block(0, 2));
			figure.add(new Block(1, 2));
		}

		return figure;
	}

}
