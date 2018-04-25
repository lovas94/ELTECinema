package projeszk.eltecinema.controller;

import projeszk.eltecinema.annotation.Role;
import projeszk.eltecinema.exception.DataNotValidException;
import projeszk.eltecinema.exception.DuplicatedDataException;
import projeszk.eltecinema.exception.MissingDataException;
import projeszk.eltecinema.exception.OverLapsException;
import projeszk.eltecinema.cinema.model.*;
import projeszk.eltecinema.cinema.service.ScreeningService;
import projeszk.eltecinema.cinema.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
public class ScreeningController implements ControllerInterface<Screening>{

    @Autowired
    private ScreeningService screeningService;

    @Override
    @Role(User.Role.ADMIN)
    @PutMapping("/update")
    public void update(@RequestBody Screening screening) {
        try {
            screeningService.update(screening);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        try {
            screeningService.deleteById(id);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        } catch (MissingDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete")
    public void delete(Screening screening) {
        try {
            screeningService.delete(screening);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }


    @Override
    @PostMapping("/create")
    @Role(User.Role.ADMIN)
    public void create(@RequestBody Screening screening) {
        try {
            screeningService.createScreening(screening);
        } catch (DuplicatedDataException | OverLapsException e) {
            e.printStackTrace();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{id}")
    public Screening get(@PathVariable Integer id) {
        return screeningService.get(id);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getall")
    public List<Screening> getAll() {
        return screeningService.getAll();
    }

    @Role({User.Role.ADMIN})
    @GetMapping("/updateMovie/{id}")
    public void updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {

        screeningService.updateMovie(id, movie);
    }

    @PostMapping("/updateCinemaRoom/{id}")
    @Role(User.Role.ADMIN)
    public void updateCinemaRoom(@PathVariable Integer id, @RequestBody CinemaRoom cinemaRoom) {
        screeningService.updateCinemaRoom(id, cinemaRoom);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getAllByMovie/{movieId}")
    public List<Screening> getScreeningsByMovie(@PathVariable Integer movieId) {
        return screeningService.getScreeningsByMovie(movieId);
    }

    @PostMapping("/addReservation/{screeningId}")
    @Role(User.Role.ADMIN)
    public void create(@PathVariable Integer screeningId, @RequestBody Reservation reservation) {
        screeningService.addReservation(screeningId, reservation);
    }
}
