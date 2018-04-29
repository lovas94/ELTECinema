package projeszk.eltecinema.dao;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import projeszk.eltecinema.enums.AgeLimit;
import projeszk.eltecinema.model.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@Transactional
public class DaoTest {

    ActorDao actorDao;
    CinemaRoomDao cinemaRoomDao;
    MovieDao movieDao;
    NewsDao newsDao;
    ReservationDao reservationDao;
    ScreeningDao screeningDao;
    UserDao userDao;

    public DaoTest(ActorDao actorDao, CinemaRoomDao cinemaRoomDao, MovieDao movieDao, NewsDao newsDao, ReservationDao reservationDao, ScreeningDao screeningDao, UserDao userDao) {
        this.actorDao = actorDao;
        this.cinemaRoomDao = cinemaRoomDao;
        this.movieDao = movieDao;
        this.newsDao = newsDao;
        this.reservationDao = reservationDao;
        this.screeningDao = screeningDao;
        this.userDao = userDao;
    }

    @Test
    public void daoTests() {
        actorDaoTest();
        newsDaoTest();
        movieDaoTest();
        screeningDaoTest();
        userDaoTest();
        actorDaoTest();
        reservationDaoTest();
    }

    @Test
    public void actorDaoTest() {
        Actor actor;
        Actor actor2;
        List<Actor> actorList;

        actor = new Actor("Test", "Test", 0, "MALE");
        actor2 = new Actor("Test2", "Test2", 0, "MALE2");

        actorList = new LinkedList<>();
        actorList.add(actor);

        // inserted with id 2
        Assert.assertEquals(true, actorDao.insertEntity(actor));

        // these data is from import.sql
        Assert.assertEquals(actorDao.findEntity(0).getName(), "Michael");
        Assert.assertEquals(actorDao.getEntities().size(), 3);
        Assert.assertEquals(actorList.get(0), actorDao.getEntities().get(2));
        Assert.assertEquals(true, actorDao.exists(actor));

        actorDao.deleteEntity(actor);
        actorDao.deleteEntityById(0);

        Assert.assertEquals(actorDao.findEntity(0), null);
        Assert.assertEquals(actorDao.findEntity(2), null);

        actorDao.insertEntity(actor2);
        actor2.setName("UPDATED");
        actorDao.updateEntity(actor2);

        Assert.assertTrue(
                actorDao.getEntities()
                        .stream()
                        .anyMatch(e -> e.getName().equals("UPDATED"))
        );
    }

    @Test
    public void cinemaRoomDaoTest() {

        CinemaRoom cinemaRoom = new CinemaRoom("TEST", 0, 0);
        CinemaRoom cinemaRoom2 = new CinemaRoom("TEST2", 0, 0);

        List<CinemaRoom> cinemaRooms = new LinkedList<>();
        cinemaRooms.add(cinemaRoom);


        Assert.assertEquals(true, cinemaRoomDao.insertEntity(cinemaRoom));

        Assert.assertEquals(cinemaRoomDao.findEntity(0).getName(), "BARTOK");
        Assert.assertEquals(cinemaRoomDao.getEntities().size(), 3);
        Assert.assertEquals(cinemaRooms.get(0), cinemaRoomDao.getEntities().get(2));
        Assert.assertEquals(true, cinemaRoomDao.exists(cinemaRoom));

        cinemaRoomDao.deleteEntity(cinemaRoom);

        Assert.assertEquals(actorDao.findEntity(2), null);
        cinemaRoomDao.insertEntity(cinemaRoom2);
        cinemaRoom2.setName("UPDATED");
        cinemaRoomDao.updateEntity(cinemaRoom2);

        Assert.assertTrue(
                cinemaRoomDao.getEntities()
                        .stream()
                        .anyMatch(e -> e.getName().equals("UPDATED"))
        );
    }

    @Test
    public void movieDaoTest() {
        Movie movie = new Movie("TEST", true, "T", "T", 0, AgeLimit.AGE_LIMIT_8, 0);
        Movie movie2 = new Movie("TEST2", true, "T2", "T2", 0, AgeLimit.AGE_LIMIT_8, 0);

        List<Movie> movies = new LinkedList<>();
        movies.add(movie);

        Assert.assertEquals(true, movieDao.insertEntity(movie));

        Assert.assertEquals(movieDao.findEntity(0).getTitle(), "PULP FICTION");
        Assert.assertEquals(movieDao.getEntities().size(), 5);
        Assert.assertEquals(movies.get(0), movieDao.getEntities().get(4));
        Assert.assertEquals(true, movieDao.exists(movie));

        movieDao.deleteEntity(movie);

        Assert.assertEquals(movieDao.findEntity(5), null);
        movieDao.insertEntity(movie2);
        movie2.setTitle("UPDATED");
        movieDao.updateEntity(movie2);

        Assert.assertTrue(
                movieDao.getEntities()
                        .stream()
                        .anyMatch(e -> e.getTitle().equals("UPDATED"))
        );
    }

    @Test
    public void newsDaoTest() {
        News news = new News("TEST", "TEST", "NULL");
        News news2 = new News("TEST2", "TEST2", "NULL2");

        List<News> newses = new LinkedList<>();
        newses.add(news);


        Assert.assertEquals(true, newsDao.insertEntity(news));

        Assert.assertEquals(newsDao.findEntity(0).getTitle(), "ASD");
        Assert.assertEquals(newsDao.getEntities().size(), 3);
        Assert.assertEquals(newses.get(0), newsDao.getEntities().get(2));
        Assert.assertEquals(true, newsDao.exists(news));

        newsDao.deleteEntity(news);

        Assert.assertEquals(actorDao.findEntity(2), null);
        newsDao.insertEntity(news2);
        news2.setTitle("UPDATED");
        newsDao.updateEntity(news2);

        Assert.assertTrue(
                newsDao.getEntities()
                        .stream()
                        .anyMatch(e -> e.getTitle().equals("UPDATED"))
        );
    }

    @Test
    public void reservationDaoTest() {
        Reservation reservation = new Reservation(1, 1);

        User user = new User("x", "x", "x", User.Role.ADMIN, "x", "x", "x", 0);
        Movie movie = new Movie("x", true, "x", "x", 0, AgeLimit.AGE_LIMIT_8, 0);

        userDao.insertEntity(user);
        movieDao.insertEntity(movie);

        reservation.setOwner(user);

        Screening screening = new Screening(movie, new Date(), new Date());
        screeningDao.insertEntity(screening);

        reservation.setScreening(screening);

        List<Reservation> reservations = new LinkedList<>();
        reservations.add(reservation);

        Assert.assertEquals(true, reservationDao.insertEntity(reservation));

        Assert.assertEquals(reservationDao.findEntity(0).getCol(), Integer.valueOf(5));
        Assert.assertEquals(reservationDao.getEntities().size(), 3);
        Assert.assertEquals(reservations.get(0), reservationDao.getEntities().get(2));
        Assert.assertEquals(true, reservationDao.exists(reservation));

        reservationDao.deleteEntity(reservation);

        Assert.assertEquals(reservationDao.findEntity(2), null);
        reservationDao.insertEntity(reservation);
        reservation.setCol(100);
        reservationDao.updateEntity(reservation);

        Assert.assertTrue(
                reservationDao.getEntities()
                        .stream()
                        .anyMatch(e -> e.getCol() == 100)
        );


    }

    @Test(expected = ConstraintViolationException.class)
    public void screeningDaoTest() {
        Screening screening;
        Screening screening2;
        List<Screening> screenings;

        Date date = new Date();
        Movie movie = new Movie("x", true, "x", "x", 0, AgeLimit.AGE_LIMIT_8, 0);
        movieDao.insertEntity(movie);

        screening = new Screening(movie, date, date);
        screening2 = new Screening(movie, new Date(), new Date());

        screenings = new LinkedList<>();
        screenings.add(screening);

        // inserted with id 2
        Assert.assertEquals(true, screeningDao.insertEntity(screening));

        // these data is from import.sql
        Assert.assertEquals(screeningDao.findEntity(0)
                .getMovie()
                .getTitle(), "PULP FICTION");

        Assert.assertEquals(screeningDao.getEntities().size(), 3);
        Assert.assertEquals(screenings.get(0), screeningDao.getEntities().get(2));
        Assert.assertEquals(true, screeningDao.exists(screening));

        screeningDao.deleteEntityById(0);
        // cant delete because of constrait violation, exception thrown


        screeningDao.deleteEntity(screening);

        Assert.assertEquals(screeningDao.findEntity(2), null);

        screeningDao.insertEntity(screening2);
        screening2.setStartTime(date);
        screeningDao.updateEntity(screening2);

        Assert.assertTrue(
                screeningDao.getEntities()
                        .stream()
                        .anyMatch(e -> e.getStartTime().equals(date))
        );
    }

    @Test(expected = ConstraintViolationException.class)
    public void userDaoTest() {
        User user;
        User user2;
        List<User> users;

        user = new User("test", "test", "test", User.Role.ADMIN, "test", "test", "test", 0);
        user2 = new User("test2", "test2", "test2", User.Role.ADMIN, "test2", "test2", "test2", 0);

        users = new LinkedList<>();
        users.add(user);

        // inserted with id 2
        Assert.assertEquals(true, userDao.insertEntity(user));

        // these data is from import.sql
        Assert.assertEquals(userDao.findEntity(0).getUsername(), "admin");

        Assert.assertEquals(userDao.getEntities().size(), 3);
        Assert.assertEquals(users.get(0), userDao.getEntities().get(2));
        Assert.assertEquals(true, userDao.exists(user));

        userDao.deleteEntityById(0);
        // cant delete because of constrait violation, exception thrown

        userDao.deleteEntity(user);

        Assert.assertEquals(userDao.findEntity(2), null);

        userDao.insertEntity(user2);
        user2.setFullName("UPDATED");
        userDao.updateEntity(user2);

        Assert.assertTrue(
                userDao.getEntities()
                        .stream()
                        .anyMatch(e -> e.getFullName().equals("UPDATED"))
        );
    }
}
