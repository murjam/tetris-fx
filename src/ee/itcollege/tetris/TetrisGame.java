package ee.itcollege.tetris;

import java.util.Timer;
import java.util.TimerTask;

import ee.itcollege.tetris.lib.CollisionDetector;
import ee.itcollege.tetris.lib.FigureGenerator;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.BlockGroup;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TetrisGame extends Application {
	
	public static void main(String[] args) {
		TetrisGame.launch(args);
	}
	
	FigureGenerator figureGenerator = new FigureGenerator();
	BlockGroup figure;
	BlockGroup fallenBlocks = new BlockGroup();
	Pane layout = new Pane();
	Stage window;
	
	
	private void createNewFigure() {
		figure = figureGenerator.createFigure();
		figure.move(10, 0);
		Platform.runLater(() -> layout.getChildren().addAll(figure));
	}
	

	@SuppressWarnings("incomplete-switch")
	@Override
	public void start(Stage window) throws Exception {
		this.window = window;
		createNewFigure();
		for (int i = 0; i < 40; i++) {
			fallenBlocks.add(new Block(0, i));
			fallenBlocks.add(new Block(19, i));
		}
		for (int i = 0; i < 20; i++) {
			fallenBlocks.add(new Block(i, 34));
		}
		layout.getChildren().addAll(fallenBlocks);
		
		Scene scene = new Scene(layout, Block.SIZE * 20, Block.SIZE * 35);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
			case UP:
				figure.rotateClockwise();
				if (CollisionDetector.collide(fallenBlocks, figure)) {
					for (int i = 0; i < 3; i++) {
						figure.rotateClockwise();
					}
				}
				break;
			case LEFT:
				figure.moveIfPossible(-1, 0, fallenBlocks);
				break;
			case RIGHT:
				figure.moveIfPossible(1, 0, fallenBlocks);
				break;
			case SPACE:
				while (figure.moveIfPossible(0, 1, fallenBlocks)) {
					;
				}
				break;
			case ESCAPE:
				gameOver();
			}
		});
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				figure.move(0, 1);
				if (CollisionDetector.collide(fallenBlocks, figure)) {
					figure.move(0, -1);
					for (Block block : figure) {
						fallenBlocks.add(block);
					}
					createNewFigure();
				}
			}
		}, 200, 200);
		
		window.setOnCloseRequest(e -> System.exit(0));
		window.setScene(scene);
		window.show();
	}
	
	private void gameOver() {
		AnchorPane prompt = new AnchorPane();
		Label nameLabel = new Label("Insert your name:");
		TextField textField = new TextField();
		textField.setOnAction(e -> {
			System.out.format("User inserted their name: %s", textField.getText());
			System.exit(0);
		});
		AnchorPane.setTopAnchor(nameLabel, 150.);
		AnchorPane.setLeftAnchor(nameLabel, 20.);
		AnchorPane.setTopAnchor(textField, 200.);
		AnchorPane.setLeftAnchor(textField, 20.);
		AnchorPane.setRightAnchor(textField, 20.);
		prompt.getChildren().addAll(nameLabel, textField);
		
		Scene scene = new Scene(prompt, Block.SIZE * 20, Block.SIZE * 35);
		window.setScene(scene);
	}

}





















