package ee.itcollege.tetris;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ee.itcollege.tetris.lib.CollisionDetector;
import ee.itcollege.tetris.lib.FigureGenerator;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TetrisGame extends Application {
	
	public static void main(String[] args) {
		TetrisGame.launch(args);
	}
	
	public static void exit(WindowEvent e) {
		System.exit(0);
	}
	
	FigureGenerator figureGenerator = new FigureGenerator();
	Figure figure = figureGenerator.createFigure();
	Rectangle gameField = new Rectangle(0, 0, Block.SIZE * 20, Block.SIZE * 20);

	private boolean figureFullyOnField() {
		List<Block> blocks = figure.getBlocks();
		for (Block block : blocks) {
			if (!CollisionDetector.contains(gameField, block)) {
				return false;
			}
		}
		return true; 
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void start(Stage window) throws Exception {
		
		Pane layout = new Pane();
		
		figure.move(9, 0);
		gameField.setFill(Color.GRAY);
		layout.getChildren().add(gameField);
		layout.getChildren().add(figure);
		
		Scene scene = new Scene(layout, gameField.getWidth(), gameField.getHeight());
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
			case UP:
				figure.rotate(); break;
			case DOWN:
				figure.move(0, 1); break;
			case LEFT:
				figure.move(-1, 0); break;
			case RIGHT:
				figure.move(1, 0); break;
			}
		});
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (!figureFullyOnField()) {
					figure.move(0, 1);
				}
			}
		}, 1000, 1000);
		
		window.setOnCloseRequest(TetrisGame::exit);
		
		window.setScene(scene);
		window.show();
	}

}
