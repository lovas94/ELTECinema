package projeszk.eltecinema.controller;

import projeszk.eltecinema.annotation.Role;
import projeszk.eltecinema.exception.DataNotValidException;
import projeszk.eltecinema.exception.DuplicatedDataException;
import projeszk.eltecinema.exception.MissingDataException;
import projeszk.eltecinema.model.CinemaRoom;
import projeszk.eltecinema.service.CinemaRoomService;
import projeszk.eltecinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemarooms")
public class CinemaRoomController implements ControllerInterface<CinemaRoom>{

    @Autowired
    private CinemaRoomService cinemaRoomService;

    @Override
    @Role(User.Role.ADMIN)
    @PutMapping("/update")
    public void update(@RequestBody CinemaRoom cinemaRoom) {
        try {
            cinemaRoomService.update(cinemaRoom);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        try {
            cinemaRoomService.deleteById(id);
        } catch (DataNotValidException | MissingDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete")
    public void delete(CinemaRoom cinemaRoom) {
        try {
            cinemaRoomService.delete(cinemaRoom);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }

    @Override
    @PostMapping("/create")
    @Role(User.Role.ADMIN)
    public void create(@RequestBody CinemaRoom cinemaRoom) {
        try {
            cinemaRoomService.create(cinemaRoom);
        } catch (DuplicatedDataException e) {
            e.printStackTrace();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{id}")
    public CinemaRoom get(@PathVariable Integer id) {
        return cinemaRoomService.get(id);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getall")
    public List<CinemaRoom> getAll() {
        return cinemaRoomService.getAll();
    }

}
