package ee.itcollege.tetris;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import ee.itcollege.tetris.dao.ScoreFileDao;
import ee.itcollege.tetris.entity.Score;
import ee.itcollege.tetris.parts.Block;
import ee.itcollege.tetris.parts.Figure;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TetrisStart extends Application {
	
	public static void main(String[] args) {
		ScoreFileDao dao = new ScoreFileDao();
		List<Score> scores = dao.loadScores();
		
		for (Score score : scores) {
			System.out.format("%d %s\n", score.getScore(), score.getPlayerName());
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is your name?");
		String name = scanner.next();
		
		System.out.println("What is your score?");
		int score = scanner.nextInt();
		scanner.close();
		
		Score sc = new Score(name, score);
		
		scores.add(sc);
		dao.persistScores(scores);
		
		
		TetrisStart.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
			
		Pane layout = new Pane();
		
		ObservableList<Node> contents = layout.getChildren();
		
		Figure figure = new Figure();
		figure.getChildren().add(new Block(-1, 0));
		figure.getChildren().add(new Block(0, 0));
		figure.getChildren().add(new Block(1, 0));
		figure.getChildren().add(new Block(0, 1));
		figure.move(9, 0);
		contents.add(figure);
		
		
		Scene scene = new Scene(layout, Block.SIZE * 20, Block.SIZE * 40);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.UP.equals(event.getCode())) {
				figure.rotateClockwise();
				//figure.move(0, -1);
				System.out.format("block absolute y: %.0f\n",
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
