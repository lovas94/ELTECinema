package hu.elte.inf.projeszk.cinema.controller;

import hu.elte.inf.projeszk.cinema.annotation.Role;
import hu.elte.inf.projeszk.cinema.exception.DataNotValidException;
import hu.elte.inf.projeszk.cinema.exception.DuplicatedDataException;
import hu.elte.inf.projeszk.cinema.exception.MissingDataException;
import hu.elte.inf.projeszk.cinema.model.Actor;
import hu.elte.inf.projeszk.cinema.service.ActorService;
import hu.elte.inf.projeszk.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController implements ControllerInterface<Actor> {

    @Autowired
    private ActorService actorService;

    @Override
    @Role(User.Role.ADMIN)
    @PostMapping("/update")
    public void update(@RequestBody Actor actor) {

        try {
            actorService.update(actor);
        } catch (DataNotValidException e) {

        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        try {
            actorService.deleteById(id);
        } catch (DataNotValidException | MissingDataException e) {

        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete")
    public void delete(Actor actor) {
        try {
            actorService.delete(actor);
        } catch (DataNotValidException e) {

        }
    }


    @Override
    @PostMapping("/create")
    @Role(User.Role.ADMIN)
    public void create(@RequestBody Actor actor) {
        try {
            actorService.create(actor);
        } catch (DuplicatedDataException e) {

        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{id}")
    public Actor get(@PathVariable Integer id) {
        return actorService.get(id);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getall")
    public List<Actor> getAll() {
        return actorService.getAll();
    }
}
