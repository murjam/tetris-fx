package ee.itcollege.tetris;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
	public void start(Stage window) throws Exception {
		
		Pane layout = new Pane();
		
		figure.move(9, 0);
		layout.getChildren().add(figure);
		
		Scene scene = new Scene(layout, Block.SIZE * 20, Block.SIZE * 40);
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.UP.equals(event.getCode())) {
				figure.rotate();
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
		
		
		
		window.setOnCloseRequest((WindowEvent event) -> {
			System.exit(0);	
		});
		
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		
		
		window.setScene(scene);
		window.show();
	}

}
