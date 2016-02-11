package GameStudio.score;

import java.util.ArrayList;
import java.util.List;

public abstract class HallOfFame {
	private String game;
	
	public HallOfFame(String game) {
		this.game = game;
	}

	public abstract void addScore(String name, int time) throws Exception;
	
	public abstract void setRating(String name, String game, int rating) throws Exception;
	
	public abstract void setComment(String name, String game, String comment) throws Exception;
	
	public abstract void returnAverage(String game) throws Exception;
	
	public abstract List<UserScore> loadScore() throws Exception;

	@Override
	public String toString() {
		List<UserScore> scores;
		try {
			scores = loadScore();
		} catch (Exception e) {
			scores = new ArrayList<>();
		}

		StringBuilder sb = new StringBuilder();
		int index = 1;
		for (UserScore score : scores) {
			sb.append(index + ". " + score.getName() + " " + score.getTime() + " " + getGame() + "\n");
			index++;
		}
		return sb.toString();
	}

	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
}