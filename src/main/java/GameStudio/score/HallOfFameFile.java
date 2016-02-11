package GameStudio.score;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HallOfFameFile extends HallOfFame {
	private final String ScoreFile = System.getProperty("user.home") + "/mnsw.scores";

	public HallOfFameFile(String game) {
		super(game);
	}

	public void addScore(String name, int time) {
		try {
			List<UserScore> scores = loadScore();
			scores.add(new UserScore(name, time, getGame()));
			Collections.sort(scores);
			save(scores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void save(List<UserScore> scores) throws IOException {
		File file = new File(ScoreFile);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(scores);
		}
	}

	@Override
	public List<UserScore> loadScore() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(ScoreFile);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				return (List) ois.readObject();
			}
		}
		return new ArrayList<UserScore>();

	}

	@Override
	public void setRating(String name, String game, int rating) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void returnAverage(String game) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setComment(String name, String game, String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
