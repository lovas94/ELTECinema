package projeszk.eltecinema.dao;

import projeszk.eltecinema.model.Reservation;
import projeszk.eltecinema.model.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class UserDao extends GenericDaoImpl<User> {

    public UserDao(Class<User> userClass, SessionFactory sessionFactor) {
        super(userClass, sessionFactor);
    }

    public User findByUsername(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.eq("username", username));
        Criteria executableCriteria = criteria.getExecutableCriteria(currentSession());
        return (User) executableCriteria.uniqueResult();
    }

    public User findByUsernameAndPassword(String username, String password) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));
        Criteria executableCriteria = criteria.getExecutableCriteria(currentSession());
        return (User) executableCriteria.uniqueResult();
    }

    public void updateUserData(String userName, String name, String email, String address, int age, String phoneNum) {
        User user = findByUsername(userName);
        if (name != null && !name.isEmpty()) {
            user.setFullName(name);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (address != null && !address.isEmpty()) {
            user.setAddress(address);
        }
        if (phoneNum != null && !phoneNum.isEmpty()) {
            user.setPhoneNumber(phoneNum);
        }
        deleteEntity(user);
        insertEntity(user);
        System.out.println(user.getFullName());
    }
    public void addReservationToUser(Integer userId, Reservation reservation) {
        User user = findEntity(userId);
        user.getReservationList().add(reservation);
        insertEntity(user);
    }
}
