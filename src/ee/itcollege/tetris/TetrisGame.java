package ee.itcollege.tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ee.itcollege.tetris.entity.User;
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
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;

public class TetrisGame extends Application {
	
	public static void main(String[] args) {
		
		EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
		List<User> users = em.createQuery("from User").getResultList();
		System.out.println(users.size());
		em.close();
		
		TetrisGame.launch(args);
	}
	
	FigureGenerator figureGenerator = new FigureGenerator();
	BlockGroup figure;
	ArrayList<Shape> fallenBlocks = new ArrayList<Shape>();
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
		fallenBlocks.add(new Rectangle(0, 0, Block.SIZE, Block.SIZE * 34));
		fallenBlocks.add(new Rectangle(Block.SIZE * 19, 0, Block.SIZE, Block.SIZE * 35));
		fallenBlocks.add(new Rectangle(0, Block.SIZE * 34, Block.SIZE * 19, Block.SIZE));
		
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
			String name = textField.getText();
			System.out.format("User inserted their name: %s", name);
			
			EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
			em.getTransaction().begin();
			User user = new User();
			user.setName(name);
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			
			System.exit(0);
		});
		AnchorPane.setTopAnchor(nameLabel, 20.);
		AnchorPane.setLeftAnchor(nameLabel, 20.);
		AnchorPane.setTopAnchor(textField, 50.);
		AnchorPane.setLeftAnchor(textField, 20.);
		AnchorPane.setRightAnchor(textField, 20.);
		prompt.getChildren().addAll(nameLabel, textField);
		
		Scene scene = new Scene(prompt, Block.SIZE * 10, Block.SIZE * 8);
		window.setScene(scene);
	}

}





















