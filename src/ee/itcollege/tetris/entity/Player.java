package ee.itcollege.tetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "allPlayers", query="from Player order by points desc")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private int points;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return String.format("%d - %s, %d points", getId(), getName(), getPoints());
	}
	
}
