package projeszk.eltecinema.controller;

import projeszk.eltecinema.annotation.Role;
import projeszk.eltecinema.exception.DataNotValidException;
import projeszk.eltecinema.exception.DuplicatedDataException;
import projeszk.eltecinema.exception.MissingDataException;
import projeszk.eltecinema.model.Reservation;
import projeszk.eltecinema.service.ReservationService;
import projeszk.eltecinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController implements ControllerInterface<Reservation> {

    @Autowired
    private ReservationService reservationService;

    @Override
    @Role(User.Role.ADMIN)
    @PutMapping("/update")
    public void update(@RequestBody Reservation reservation) {
        try {
            reservationService.update(reservation);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        try {
            reservationService.deleteById(id);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        } catch (MissingDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete")
    public void delete(Reservation reservation) {
        try {
            reservationService.delete(reservation);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }


    @Override
    @PostMapping("/create")
    @Role(User.Role.ADMIN)
    public void create(@RequestBody Reservation reservation) {
        try {
            reservationService.create(reservation);
        } catch (DuplicatedDataException e) {
            e.printStackTrace();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{id}")
    public Reservation get(@PathVariable Integer id) {
        return reservationService.get(id);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getall")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getAllToUser/{userId}")
    public List<Reservation> getAllToUser(@PathVariable Integer userId) {
        return reservationService.getAllReservationsToUser(userId);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getAllToScreening/{screeningId}")
    public List<Reservation> getAllToScreening(@PathVariable Integer screeningId) {
        return reservationService.getAllReservationsToScreening(screeningId);
    }

}

