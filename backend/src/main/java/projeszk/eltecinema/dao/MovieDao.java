package projeszk.eltecinema.dao;

import projeszk.eltecinema.model.Actor;
import projeszk.eltecinema.model.Movie;
import projeszk.eltecinema.model.Screening;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class MovieDao extends GenericDaoImpl<Movie> {

    public MovieDao(Class<Movie> movieClass, SessionFactory sessionFactor) {
        super(movieClass, sessionFactor);
    }

    public List<Movie> getMoviesByActor(Integer actorId) {
        Query query = currentSession().createQuery("SELECT m FROM Movie m JOIN m.actors a WHERE a.id = :actorId");
        query.setParameter("actorId", actorId);
        return (List<Movie>) query.list();
    }

    public void addActorToMovie(Integer movieId, Actor actor) {
        Movie movie = findEntity(movieId);
        movie.getActors().add(actor);
        updateEntity(movie);
    }

    public void addScreeningToMovie(Integer movieId, Screening screening) {
        Movie movie = findEntity(movieId);
        movie.getScreenings().add(screening);
        updateEntity(movie);
    }

    public List<Movie> getByDirector(String director) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.eq("director", director));
        Criteria executableCriteria = criteria.getExecutableCriteria(currentSession());
        return (List<Movie>) executableCriteria.list();
    }

    public List<Movie> getByDub(boolean dubbed) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.eq("dubbed", dubbed));
        Criteria executableCriteria = criteria.getExecutableCriteria(currentSession());
        return (List<Movie>) executableCriteria.list();
    }

    public List<Movie> getByTitle(String title) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.eq("title", title));
        Criteria executableCriteria = criteria.getExecutableCriteria(currentSession());
        return (List<Movie>) executableCriteria.list();
    }

}
