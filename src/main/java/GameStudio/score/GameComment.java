package GameStudio.score;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Recenzia")
public class GameComment implements Serializable{
	private static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "meno")
	private String name;
	@Column(name = "recenzia")
	private String comment;
	@Column(name = "game")
	private String game;
	
	
	public GameComment(){
		
	}

	public GameComment(String name, String comment, String game) {
		super();
		this.name = name;
		this.comment = comment;
		this.game = game;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "GameComment [name=" + name + ", comment=" + comment + ", game=" + game + "]";
	}
	
}
