package projeszk.eltecinema.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import projeszk.eltecinema.dao.*;
import projeszk.eltecinema.model.*;

public class DaoConfigTest {

    @Autowired
    SessionFactory sessionFactory;

    @Bean
    MovieDao movieDao() {
        return new MovieDao(Movie.class, sessionFactory);
    }

    @Bean
    ScreeningDao screeningDao() {
        return new ScreeningDao(Screening.class, sessionFactory);
    }

    @Bean
    ActorDao actorDao() {
        return new ActorDao(Actor.class, sessionFactory);
    }

    @Bean
    CinemaRoomDao cinemaRoomDao() {
        return new CinemaRoomDao(CinemaRoom.class, sessionFactory);
    }

    @Bean
    UserDao userDao() {
        return new UserDao(User.class, sessionFactory);
    }

    @Bean
    ReservationDao ReservationDao() { return new ReservationDao(Reservation.class, sessionFactory); }

    @Bean
    NewsDao NewsDao() { return new NewsDao(News.class, sessionFactory); }
}

