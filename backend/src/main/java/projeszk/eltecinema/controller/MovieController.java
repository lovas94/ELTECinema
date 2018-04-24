package hu.elte.inf.projeszk.cinema.controller;

import hu.elte.inf.projeszk.cinema.annotation.Role;
import hu.elte.inf.projeszk.cinema.exception.DataNotValidException;
import hu.elte.inf.projeszk.cinema.exception.DuplicatedDataException;
import hu.elte.inf.projeszk.cinema.exception.MissingDataException;
import hu.elte.inf.projeszk.cinema.model.Actor;
import hu.elte.inf.projeszk.cinema.model.Movie;
import hu.elte.inf.projeszk.cinema.model.Screening;
import hu.elte.inf.projeszk.cinema.service.MovieService;
import hu.elte.inf.projeszk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController implements ControllerInterface<Movie>{

    @Autowired
    private MovieService movieService;

    @Override
    @Role(User.Role.ADMIN)
    @PutMapping("/update")
    public void update(@RequestBody Movie movie) {
        try {
            movieService.update(movie);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        try {
            movieService.deleteById(id);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        } catch (MissingDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete")
    public void delete(Movie movie) {
        try {
            movieService.delete(movie);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }


    @Override
    @PostMapping("/create")
    @Role(User.Role.ADMIN)
    public void create(@RequestBody Movie movie) {
        try {
            movieService.create(movie);
        } catch (DuplicatedDataException e) {
            e.printStackTrace();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{id}")
    public Movie get(@PathVariable Integer id) {
        return movieService.get(id);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getall")
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getByActor/{actorId}")
    public List<Movie> getByActor(@PathVariable Integer actorId) {
        return movieService.getMoviesByActor(actorId);
    }

    @Role({User.Role.ADMIN})
    @PostMapping("/addActorToMovie/{movieId}")
    public void addActorToMovie(@PathVariable Integer movieId, @RequestBody Actor actor) {
        movieService.addActorToMovie(movieId, actor.getId());
    }

    @Role({User.Role.ADMIN})
    @PostMapping("/addMovieToScreening/{movieId}")
    public void addActorToMovie(@PathVariable Integer movieId, @RequestBody Screening screening) {
        movieService.addMovieToScreening(movieId, screening);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getByDirector/{director}")
    public List<Movie> getByDirector(@PathVariable String director) {
        return movieService.getByDirector(director);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getByDub/{dubbed}")
    public List<Movie> getByDub(@PathVariable boolean dubbed) {
        return movieService.getByDub(dubbed);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getByTitle/{title}")
    public List<Movie> getByTitle(@PathVariable String title) {
        return movieService.getByTitle(title);
    }
}
