package projeszk.eltecinema.controller;

import projeszk.eltecinema.annotation.Role;
import projeszk.eltecinema.dao.UserDao;
import projeszk.eltecinema.exception.DataNotValidException;
import projeszk.eltecinema.exception.DuplicatedDataException;
import projeszk.eltecinema.exception.MissingDataException;
import projeszk.eltecinema.exception.UserNotValidException;
import projeszk.eltecinema.service.UserService;
import projeszk.eltecinema.model.Reservation;
import projeszk.eltecinema.model.User;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/")
public class UserController implements ControllerInterface<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    private User user;

    @Override
    @Role(User.Role.ADMIN)
    @PostMapping("/update")
    public void update(@RequestBody User user) {
        try {
            userService.update(user);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
        } catch (DataNotValidException | MissingDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Role(User.Role.ADMIN)
    @DeleteMapping("/delete")
    public void delete(@RequestBody User user) {
        try {
            userService.delete(user);
        } catch (DataNotValidException e) {
            e.printStackTrace();
        }
    }


    @Override
    @PostMapping("/create")
    @Role(User.Role.ADMIN)
    public void create(@RequestBody User user) {
        try {
            userService.create(user);
        } catch (DuplicatedDataException e) {
            e.printStackTrace();
        }
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getall")
    public List<User> getAll() {
        return userDao.getEntities();
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new User());
        return "\"login\"";
    }

    @PostMapping("/login")
    public User login(@ModelAttribute User user, Model model) {
        try {
            return userService.login(user);
        }
        catch (UserNotValidException e) {
            model.addAttribute("error", true);
            return null;
        }
    }

    /*
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        try {
            userService.login(user);
            return "\"" + redirectToGreeting(user) + "\"";
        }
        catch (UserNotValidException e) {
            model.addAttribute("error", true);
            return "\"login\"";
        }
    }
*/

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "\"register\"";
    }

    @PostMapping("/register")
    public User register(@ModelAttribute User user) {
        user.setRole(User.Role.USER);
        User retUser = userService.register(user);
        if (retUser == null) {
            assert false;
        }
        return retUser;
    }

    private String redirectToGreeting(@ModelAttribute User user) {
        return "redirect:/user/greet?name=" + user.getUsername();
    }

    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping("/addReservation/{userId}")
    @Role(User.Role.ADMIN)
    public void create(@PathVariable Integer userId, @RequestBody Reservation reservation) {
        userService.addReservation(userId, reservation);
    }

    @PostMapping("/logout")
    @Role(User.Role.USER)
    public String logout() {
        try {
            userService.logout();
            return "done";
        } catch (UserNotValidException ex) {
            return "not_logged_in";
        }
    }

    @GetMapping("/getCurrentUser")
    @Role(User.Role.USER)
    public String getCurrentUser() {
        return userService.getLoggedInUserName();
    }

    @GetMapping("/updateUserData")
    @Role(User.Role.USER)
    public String updateData(@RequestParam(value = "username") String username,
                             @RequestParam(value = "fullName") String fullName,
                             @RequestParam(value = "address") String address,
                             @RequestParam(value = "phoneNumber") String phoneNumber,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "age") Integer age) {
        userService.updateUserData(username, fullName, email,address, age, phoneNumber);
        return "\""+"updated"+"\"";
    }

    @GetMapping("/getUserByUsername")
    public User getByUserName(@RequestParam(value = "username") String userName) {
        return userService.getUserDao().findByUsername(userName);
    }

    @GetMapping("/generateConfCode")
    @Role(User.Role.USER)
    public String generateAndSendConfirmationCode(@RequestParam(value = "mail") String semail) throws EmailException {
        String code = UUID.randomUUID().toString();
        return "{\"code\":\""+code+"\"}";
    }
}