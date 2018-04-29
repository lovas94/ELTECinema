package projeszk.eltecinema.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import projeszk.eltecinema.enums.AgeLimit;
import projeszk.eltecinema.exception.DataNotValidException;
import projeszk.eltecinema.exception.DuplicatedDataException;
import projeszk.eltecinema.exception.UserNotValidException;
import projeszk.eltecinema.model.*;

import java.util.Date;

@RunWith(SpringRunner.class)
@Transactional
public class ServiceTest {

    ActorService actorService;
    CinemaRoomService cinemaRoomService;
    MovieService movieService;
    NewsService newsService;
    ReservationService reservationService;
    ScreeningService screeningService;
    UserService userService;

    public ServiceTest(ActorService actorService, CinemaRoomService cinemaRoomService, MovieService movieService, NewsService newsService, ReservationService reservationService, ScreeningService screeningService, UserService userService) {
        this.actorService = actorService;
        this.cinemaRoomService = cinemaRoomService;
        this.movieService = movieService;
        this.newsService = newsService;
        this.reservationService = reservationService;
        this.screeningService = screeningService;
        this.userService = userService;
    }

    @Test
    public void serviceTests() {
        actorServiceTest();
        cinemaRoomServiceTest();
        newsServiceTest();
        screeningServiceTest();
        reservationServiceTest();
        userServiceTest();
    }

    @Test
    public void actorServiceTest() {
        Actor actor = new Actor("TEST", "TEST", 0, "TEST");

        try {
            actorService.create(actor);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        Actor actor1 = actorService.get(1);

        Assert.assertNotNull(actor1);

        Assert.assertEquals(actorService.notExist(actor1), false);
        Assert.assertEquals(actorService.exist(actor1), true);

        Assert.assertEquals(actorService.getAll().size(), 3);

        try {
            actorService.delete(actor1);
        } catch (DataNotValidException ex) {
            assert false;
        }

        Actor actor2 = actorService.get(0);
        actor2.setName("UPDATED");
        
        try {
            actorService.update(actor2);
        } catch (DataNotValidException ex) {
            assert false;
        }
    }
    
    @Test
    public void cinemaRoomServiceTest() {
        CinemaRoom cinemaRoom = new CinemaRoom("TEST", 0, 0);

        try {
            cinemaRoomService.create(cinemaRoom);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        CinemaRoom cinemaRoom1 = cinemaRoomService.get(2);

        Assert.assertNotNull(cinemaRoom1);

        Assert.assertEquals(cinemaRoomService.notExist(cinemaRoom1), false);
        Assert.assertEquals(cinemaRoomService.exist(cinemaRoom1), true);

        Assert.assertEquals(cinemaRoomService.getAll().size(), 3);

        try {
            cinemaRoomService.delete(cinemaRoom1);
        } catch (DataNotValidException ex) {
            assert false;
        }

        cinemaRoom.setName("UPDATED");

        CinemaRoom cinemaRoom2 = cinemaRoomService.get(1);
        cinemaRoom2.setName("UPDATED");

        try {
            cinemaRoomService.update(cinemaRoom2);
        } catch (DataNotValidException ex) {
            assert false;
        }
    }

    @Test
    public void movieServiceTest() {
        Movie movie = new Movie("TEST", true, "TEST", "TEST", 0, AgeLimit.AGE_LIMIT_8, 0);

        try {
            movieService.create(movie);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        Movie movie1 = movieService.get(2);

        Assert.assertNotNull(movie1);

        Assert.assertEquals(movieService.notExist(movie1), false);
        Assert.assertEquals(movieService.exist(movie1), true);

        Assert.assertEquals(movieService.getAll().size(), 3);

        try {
            movieService.delete(movie1);
        } catch (DataNotValidException ex) {
            assert false;
        }

        movie.setTitle("UPDATED");

        Movie movie2 = movieService.get(1);
        movie2.setTitle("UPDATED");

        try {
            movieService.update(movie2);
        } catch (DataNotValidException ex) {
            assert false;
        }

        Assert.assertEquals(movieService.getMoviesByActor(0).size(), 1);
        Assert.assertEquals(movieService.getByDirector("TARANTINO").size(), 4);
        Assert.assertEquals(movieService.getByDub(true).size(), 2);
        Assert.assertEquals(movieService.getByTitle("PULP FICTION").size(), 1);
    }

    @Test
    public void newsServiceTest() {
        News news = new News("TEST", "TEST", "TEST");

        try {
            newsService.create(news);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        News news1 = newsService.get(2);

        Assert.assertNotNull(news1);

        Assert.assertEquals(newsService.notExist(news1), false);
        Assert.assertEquals(newsService.exist(news1), true);

        Assert.assertEquals(newsService.getAll().size(), 3);

        try {
            newsService.delete(news1);
        } catch (DataNotValidException ex) {
            assert false;
        }

        news.setTitle("UPDATED");

        News news2 = newsService.get(1);
        news2.setTitle("UPDATED");

        try {
            newsService.update(news2);
        } catch (DataNotValidException ex) {
            assert false;
        }
    }

    @Test
    public void reservationServiceTest() {
        Reservation reservation = new Reservation(1, 1);

        User user = new User("x", "x", "x", User.Role.ADMIN, "x", "x", "x", 0);
        Movie movie = new Movie("x", true, "x", "x", 0, AgeLimit.AGE_LIMIT_8, 0);
        Screening screening = new Screening(movie, new Date(), new Date());

        try {
            userService.create(user);
            movieService.create(movie);
            screeningService.create(screening);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        reservation.setOwner(user);
        reservation.setScreening(screening);

        try {
            reservationService.create(reservation);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        Reservation reservation1 = reservationService.get(2);

        Assert.assertNotNull(reservation1);

        Assert.assertEquals(reservationService.notExist(reservation1), false);
        Assert.assertEquals(reservationService.exist(reservation1), true);

        Assert.assertEquals(reservationService.getAll().size(), 3);

        try {
            reservationService.delete(reservation1);
        } catch (DataNotValidException ex) {
            assert false;
        }

        Reservation reservation2 = reservationService.get(1);

        try {
            reservationService.update(reservation2);
        } catch (DataNotValidException ex) {
            assert false;
        }

        Assert.assertEquals(reservationService.getAllReservationsToUser(0).size(), 2);
        Assert.assertEquals(reservationService.getAllReservationsToScreening(0).size(), 1);
    }
    
    @Test
    public void screeningServiceTest() {

        Movie movie = new Movie("x", true, "x", "x", 0, AgeLimit.AGE_LIMIT_8, 0);
        Screening screening = new Screening(movie, new Date(), new Date());

        try {
            movieService.create(movie);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        try {
            screeningService.create(screening);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        Screening screening1 = screeningService.get(2);

        Assert.assertNotNull(screening1);

        Assert.assertEquals(screeningService.notExist(screening1), false);
        Assert.assertEquals(screeningService.exist(screening1), true);

        Assert.assertEquals(screeningService.getAll().size(), 3);

        try {
            screeningService.delete(screening1);
        } catch (DataNotValidException ex) {
            assert false;
        }

        Screening screening2 = screeningService.get(1);

        try {
            screeningService.update(screening2);
        } catch (DataNotValidException ex) {
            assert false;
        }
    }

    @Test
    public void userServiceTest() {
        User user = new User("test", "test", "test", User.Role.ADMIN, "x", "x", "x", 0);

        try {
            userService.login(user);
        } catch (UserNotValidException ex) {
            assert true;
        }

        User user1 = userService.getUserDao().findEntity(0);

        try {
            userService.login(user1);
        } catch (UserNotValidException ex) {
            assert false;
        }

        userService.register(user);

        User user2 = userService.getUserDao().findByUsername("test");
        Assert.assertNotNull(user2);

        Reservation reservation = new Reservation(1, 1);

        Movie movie = new Movie("x", true, "x", "x", 0, AgeLimit.AGE_LIMIT_8, 0);
        Screening screening = new Screening(movie, new Date(), new Date());

        try {
            movieService.create(movie);
            screeningService.create(screening);
        } catch (DuplicatedDataException ex) {
            assert false;
        }

        reservation.setOwner(user);
        reservation.setScreening(screening);

        userService.addReservation(0, reservation);

        Assert.assertEquals(reservationService.getAllReservationsToUser(0).size(), 2);
    }
}

