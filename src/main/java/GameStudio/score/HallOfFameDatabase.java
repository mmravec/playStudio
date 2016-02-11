package GameStudio.score;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallOfFameDatabase extends HallOfFame {

	private static final String URL = "jdbc:mysql://localhost:3306/test";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	static final String SELECT_SCORE = "select * from score where game = ? order by time";
	static final String INSERT_SCORE = "insert into score( name, time, game) values(?,?, ?)";

	public HallOfFameDatabase(String game) {
		super(game);
	}

	public void addScore(String name, int time) throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT_SCORE)) {

			statement.setString(1, name);
			statement.setInt(2, time);
			statement.setString(3, getGame());
			statement.executeUpdate();
		}
	}

	@Override
	public List<UserScore> loadScore() throws Exception {
		List<UserScore> scores = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SELECT_SCORE)) {

			statement.setString(1, getGame());

			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					scores.add(new UserScore(rs.getString(2), rs.getInt(3), rs.getString(4)));
				}
			}
		}
		return scores;
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
