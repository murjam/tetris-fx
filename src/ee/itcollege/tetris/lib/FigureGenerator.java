package ee.itcollege.tetris.lib;

import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;

public class FigureGenerator {

	public Figure createFigure() {

		Figure figure = new Figure();

		figure.add(new Block(0, 0));
		figure.add(new Block(-1, 0));
		figure.add(new Block(1, 0));
		figure.add(new Block(0, 1));

		return figure;
	}

}
