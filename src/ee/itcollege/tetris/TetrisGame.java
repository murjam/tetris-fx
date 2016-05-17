package ee.itcollege.tetris;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ee.itcollege.tetris.lib.CollisionDetector;
import ee.itcollege.tetris.lib.FigureGenerator;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TetrisGame extends Application {
	
	public static void main(String[] args) {
		TetrisGame.launch(args);
	}
	
	FigureGenerator figureGenerator = new FigureGenerator();
	Figure figure = figureGenerator.createFigure();
	Group fallenBlocks = new Group();
	

	@SuppressWarnings("incomplete-switch")
	@Override
	public void start(Stage window) throws Exception {
		Pane layout = new Pane();
		
		figure.move(9, 0);
		layout.getChildren().add(figure);
		layout.getChildren().add(fallenBlocks);
		
		Scene scene = new Scene(layout, Block.SIZE * 20, Block.SIZE * 40);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
			case UP:
				figure.rotateClockwise();
				break;
			case LEFT:
				figure.move(-1, 0);
				break;
			case RIGHT:
				figure.move(1, 0);
				break;
			case ESCAPE:
				System.exit(0);
			}
//				System.out.format("first block absolute y: %.0f\n",
//						figure.getChildren().get(0).getLocalToSceneTransform().getTy());
		});
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				figure.move(0, 1);
				
				if (figure.getLayoutY() >= scene.getHeight() - figure.getBoundsInParent().getHeight()
						|| CollisionDetector.collide(fallenBlocks, figure)) {
					Platform.runLater(() -> {
						List<Block> blocks = figure.breakUp();
						fallenBlocks.getChildren().addAll(blocks);
						figure = figureGenerator.createFigure();
						layout.getChildren().add(figure);
					});
				}
			}
		}, 200, 200);		
		
		window.setOnCloseRequest(e -> System.exit(0));
		window.setScene(scene);
		window.show();
	}

}





















