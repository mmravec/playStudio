package GameStudio.score;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

//@Component
public class HallOfFameHibernate extends HallOfFame {

	public HallOfFameHibernate(String game) {
		super(game);
	}

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void addScore(String name, int time) throws Exception {
		em.persist(new UserScore(name, time, getGame()));

	}

	@Transactional
	@Override
	public List<UserScore> loadScore() throws Exception {
		return em.createQuery("select s from UserScore s where s.game = :name order by time", UserScore.class)
				.setParameter("name", getGame()).getResultList();
	}

	@Transactional
	@Override
	public void setRating(String name, String game, int rating) throws Exception {
		List<GameRating> list = em
				.createQuery("select r from GameRating r where game = :game and name = :name", GameRating.class)
				.setParameter("game", game).setParameter("name", name).getResultList();
		if (list.isEmpty())
			em.persist(new GameRating(name, rating, game));
		else
			list.get(0).setRating(rating);
	}

	//("SELECT AVG(CAST(s.transfusionUnits as double))
	
	@Override
	public void returnAverage(String game) throws Exception {
		Query q = (Query) em.createQuery("select avg(s.rating) from GameRating s");
		System.out.println(q);
		
	}
	@Transactional
	@Override
	public void setComment(String name, String game, String comment) throws Exception {
		List<GameComment> list = em
				.createQuery("select r from GameComment r where game = :game and name = :name", GameComment.class)
				.setParameter("game", game).setParameter("name", name).getResultList();
		if (list.isEmpty())
			em.persist(new GameComment(name, game, comment));
		else
			list.get(0).setComment(comment);
	}

}
