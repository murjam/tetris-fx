package ee.itcollege.tetris;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ee.itcollege.tetris.lib.FigureGenerator;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;

public class TetrisGame extends Application {
	
	public static void main(String[] args) {
		TetrisGame.launch(args);
	}
	
	
	FigureGenerator figureGenerator = new FigureGenerator();
	Figure figure = figureGenerator.createFigure();

	@Override
	public void start(Stage stage) throws Exception {
		
		Pane layout = new Pane();
		
		figure.move(9, 0);
		layout.getChildren().add(figure);
		
		Scene scene = new Scene(layout, Block.SIZE * 20, Block.SIZE * 40);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.UP.equals(event.getCode())) {
				figure.move(0, -1);
				System.out.format("first block absolute y: %.0f\n",
						figure.getChildren().get(0).getLocalToSceneTransform().getTy());
			}
		});
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				figure.move(0, 1);
			}
		}, 1000, 1000);
		
		stage.setOnCloseRequest(e -> System.exit(0));
		stage.setScene(scene);
		stage.show();
	}

}
