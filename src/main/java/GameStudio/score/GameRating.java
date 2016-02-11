package GameStudio.score;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rejting")
public class GameRating implements Serializable, Comparable<GameRating> {
	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "meno")
	private String name;
	@Column(name = "hodnotenie")
	private int rating;
	@Column(name = "game")
	private String game;

	public GameRating() {

	}

	public GameRating(String name, int rating, String game) {
		this.name = name;
		this.rating = rating;
		this.game = game;
	}

	public String getName() {
		return name;
	}

	public int getRating() {
		return rating;
	}

	public String getGame() {
		return game;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setGame(String game) {
		this.game = game;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(GameRating arg0) {
		return rating - arg0.rating;
	}

	@Override
	public String toString() {
		return "GameRating [name= " + name + " rating= " + rating + " game= " + game + "]";
	}

}
