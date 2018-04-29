package projeszk.eltecinema.service;


import projeszk.eltecinema.dao.ActorDao;
import projeszk.eltecinema.dao.MovieDao;
import projeszk.eltecinema.model.Movie;
import projeszk.eltecinema.model.Screening;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Service
@SessionScope
@Data
public class MovieService extends AbstractService<Movie> {

    @Autowired
    MovieDao movieDao;

    @Autowired
    ActorDao actorDao;


    public List<Movie> getMoviesByActor(Integer actorId) {
        return movieDao.getMoviesByActor(actorId);
    }

    public void addActorToMovie(Integer id, Integer actorid) {
        movieDao.addActorToMovie(id, actorDao.findEntity(actorid));
    }
    public void addMovieToScreening(Integer movieId, Screening screening) {
        movieDao.addScreeningToMovie(movieId, screening);
    }

    public List<Movie> getByDirector(String director) {
        return movieDao.getByDirector(director);
    }

    public List<Movie> getByDub(boolean dubbed) {
        return movieDao.getByDub(dubbed);
    }

    public List<Movie> getByTitle(String title) {
        return movieDao.getByTitle(title);
    }
}
