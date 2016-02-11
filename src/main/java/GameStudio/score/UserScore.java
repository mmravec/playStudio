package GameStudio.score;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "score")
public class UserScore implements Serializable, Comparable<UserScore> {
	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String name;
	private int time;
	private String game;

	public UserScore() {
	}

	public UserScore(String name, int time, String game) {
		this.name = name;
		this.time = time;
		this.game = game;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public int getTime() {
		return time;
	}

	public String getGame() {
		return game;
	}

	@Override
	public int compareTo(UserScore o) {
		return time - o.time;
	}

	@Override
	public String toString() {
		return "UserScore [name=" + name + ", time=" + time + ", game=" + game + "]";
	}

}
