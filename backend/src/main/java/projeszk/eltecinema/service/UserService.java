package projeszk.eltecinema.service;

import projeszk.eltecinema.dao.UserDao;
import projeszk.eltecinema.exception.UserNotValidException;
import projeszk.eltecinema.model.Reservation;
import projeszk.eltecinema.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Objects;

@Service
@SessionScope
@Data
public class UserService extends AbstractService<User> {

    @Autowired
    private UserDao userDao;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userDao.findByUsername(user.getUsername());
        }
        throw new UserNotValidException("Username already exists!");
    }

    public void logout() throws UserNotValidException {
        if (user == null) {
            throw new UserNotValidException("You are not logged in!");
        } else {
            user = null;
        }
    }

    public User register(User user) {
        userDao.insertEntity(user);
        return this.user = userDao.findByUsername(user.getUsername());
    }

    public boolean isValid(User user) {
        User foundUser = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return foundUser != null &&
                Objects.equals(foundUser.getEmail(), user.getEmail());
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public User getLoggedInUser() {
        return user;
    }

    public String getLoggedInUserName() {
        return user == null ? "Unknown" : user.getUsername();
    }

    public void addReservation(Integer userId, Reservation reservation) {
        userDao.addReservationToUser(userId, reservation);
    }

    public void updateUserData(String userName, String name, String email, String address, int age, String phoneNum) {
        userDao.updateUserData(userName, name, email, address, age, phoneNum);
        this.user = userDao.findByUsername(userName);
    }
}
