package projeszk.eltecinema.service;


import projeszk.eltecinema.dao.ActorDao;
import projeszk.eltecinema.dao.MovieDao;
import projeszk.eltecinema.dao.ScreeningDao;
import projeszk.eltecinema.exception.DataNotValidException;
import projeszk.eltecinema.exception.DuplicatedDataException;
import projeszk.eltecinema.exception.OverLapsException;
import projeszk.eltecinema.model.CinemaRoom;
import projeszk.eltecinema.model.Movie;
import projeszk.eltecinema.model.Reservation;
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
public class ScreeningService extends AbstractService<Screening> {

    @Autowired
    MovieDao movieDao;

    @Autowired
    private ActorDao actorDao;

    @Autowired
    private ScreeningDao screeningDao;

    public boolean overlapsWithAny(Screening screening) {
        return screeningDao.getEntities()
                .stream()
                .anyMatch(s -> s.screeningOverLapsWith(screening));
    }

    public void createScreening(Screening screening) throws DuplicatedDataException, OverLapsException {
        if (notExist(screening)) {
            if (!overlapsWithAny(screening)) {
                screeningDao.insertEntity(screening);
            } else {
                throw new OverLapsException("Screening overlaps with another!");
            }
        } else {
            throw new DuplicatedDataException();
        }
    }

    public void updateScreening(Screening screening) throws DataNotValidException, OverLapsException {
        if (notExist(screening)) {
            if (!overlapsWithAny(screening)) {
                dao.updateEntity(screening);
            } else {
                throw new OverLapsException("Screening overlaps with another!");
            }
        } else {
            throw new DataNotValidException();
        }
    }
    public void updateMovie(Integer id, Movie movie) {
        screeningDao.updateMovie(id, movie);
    }

    public void updateCinemaRoom(Integer id, CinemaRoom cinemaRoom) {
        screeningDao.updateCinemaRoom(id, cinemaRoom);
    }

    public List<Screening> getScreeningsByMovie(Integer movieId) {
        return screeningDao.getScreeningsByMovie(movieId);
    }

    public void addReservation(Integer id, Reservation reservation) {
        screeningDao.addReservationToScreening(id, reservation);
    }
}
