package ee.itcollege.tetris.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ee.itcollege.tetris.entity.Score;

public class ScoreFileDao {
	
	public List<Score> loadScores() {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File("db/scores.dat")));
			@SuppressWarnings("unchecked")
			List<Score> scores = (List<Score>) input.readObject();
			input.close();
			return scores;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Score>();
	}
	
	public void persistScores(List<Score> scores) {
		
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("db/scores.dat")));
			output.writeObject(scores);
			output.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
