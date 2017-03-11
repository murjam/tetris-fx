package ee.itcollege.tetris.scene;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ee.itcollege.tetris.entity.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GameOverScene extends Scene {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("top10");

	public GameOverScene(VBox root) {
		super(root);
		root.getChildren().add(new Label("Insert your name"));
		TextField field = new TextField();
		root.getChildren().add(field);
		Button button = new Button("Submit");
		root.getChildren().add(button);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			EntityManager em = emf.createEntityManager();
			
			EntityTransaction transaction = em.getTransaction();
			
			transaction.begin();
			
			Player player = new Player();
			player.setName(field.getText());
			player.setPoints((int)(Math.random() * 100));
			em.persist(player);
			transaction.commit();
			em.close();
			emf.close();
			
			System.exit(0);
		});
		
		EntityManager em = emf.createEntityManager();
		List<Player> players =
				em.createQuery("from Player order by points desc", Player.class)
				.setMaxResults(10).getResultList();
		
		for (Player p : players) {
			Label label = new Label(p.toString());
			label.setFont(new Font(20));
			root.getChildren().add(label);
		}
		em.close();
		
	}

}

