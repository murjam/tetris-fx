package ee.itcollege.tetris;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ee.itcollege.tetris.lib.CollisionDetector;
import ee.itcollege.tetris.lib.FigureGenerator;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TetrisGame extends Application {

	public static final int FIELD_HEIGHT = 30;
	public static final int FIELD_WIDHT = 15;

	FigureGenerator figureGenerator = new FigureGenerator();
	Figure figure = figureGenerator.createFigure();
	ArrayList<Shape> gameField = new ArrayList<>();
	Pane layout = new Pane();

	private void createField() {
		// left
		Rectangle rectangle = new Rectangle(
				0, 0, Block.SIZE, FIELD_HEIGHT * Block.SIZE);
		rectangle.setFill(Color.GRAY);
		gameField.add(rectangle);
		// right
		rectangle = new Rectangle(
				(FIELD_WIDHT - 1) * Block.SIZE, 0, Block.SIZE, FIELD_HEIGHT * Block.SIZE);
		rectangle.setFill(Color.GRAY);
		gameField.add(rectangle);
		
		// bottom
		rectangle = new Rectangle(
				0, (FIELD_HEIGHT - 1) * Block.SIZE, FIELD_WIDHT * Block.SIZE, Block.SIZE);
		rectangle.setFill(Color.GRAY);
		gameField.add(rectangle);
		
		layout.getChildren().addAll(gameField);
		

	}
	
	private void createNewFigure() {
		Platform.runLater(() -> {
			gameField.addAll(figure);
			figure = figureGenerator.createFigure();
			figure.move(FIELD_WIDHT / 2, 0);
			layout.getChildren().addAll(figure);
		});
	}
	
	
	@Override
	public void start(Stage window) throws Exception {
		createField();
		createNewFigure();
		
		Scene scene = new Scene(layout,
				Block.SIZE * FIELD_WIDHT,
				Block.SIZE * FIELD_HEIGHT);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (!figure.moveIfDidntCollide(gameField, 0, 1)) {
					createNewFigure();
				}
			}
		}, 400, 400);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);

		window.setOnCloseRequest(e -> System.exit(0));
		window.setScene(scene);
		window.show();
	}
	
	private void handleKeyPress(KeyEvent event) {
		switch (event.getCode()) {
		case RIGHT:
			figure.moveIfDidntCollide(gameField, 1, 0); break;
		case LEFT:
			figure.moveIfDidntCollide(gameField, -1, 0); break;
		case UP:
			figure.rotateIfNotCollides(gameField); break;
		case DOWN:
			figure.moveIfDidntCollide(gameField, 0, 1); break;
		case SPACE:
			while (figure.moveIfDidntCollide(gameField, 0, 1))
				;
			createNewFigure();
			break;
		
		case ESCAPE:
			System.exit(0);
		
		default:
		}
	}
	
	public static void main(String[] args) {
		TetrisGame.launch(args);
	}

}
