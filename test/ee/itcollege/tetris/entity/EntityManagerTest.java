package ee.itcollege.tetris.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManagerTest {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("top10");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		int howManyDeleted = em.createQuery("delete from Player").executeUpdate();
		System.out.format("Deleted %d players\n", howManyDeleted);
		
//		Player player = new Player();
//		player.setName("Mati");
//		player.setPoints((int)(Math.random() * 100));
//		em.persist(player);
		transaction.commit();
		
		List<Player> players =
				em.createNamedQuery("allPlayers", Player.class).getResultList();
		
		for (Player p : players) {
			System.out.println(p);
		}
		em.close();
		emf.close();
	}
	
}
