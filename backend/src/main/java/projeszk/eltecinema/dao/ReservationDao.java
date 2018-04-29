package projeszk.eltecinema.dao;

import projeszk.eltecinema.model.Reservation;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class ReservationDao extends GenericDaoImpl<Reservation> {

    public ReservationDao(Class<Reservation> reservationClass, SessionFactory sessionFactor) {
        super(reservationClass, sessionFactor);
    }

    public List<Reservation> getAllReservationsToScreening(Integer screeningId) {
        Query query = currentSession().createQuery("SELECT r FROM Screening s JOIN s.reservations r WHERE s.id = :screeningId");
        query.setParameter("screeningId", screeningId);
        return (List<Reservation>) query.list();
    }

    public List<Reservation> getAllReservationToUser(Integer userId) {
        Query query = currentSession().createQuery("SELECT r FROM User u JOIN u.reservationList r WHERE u.id = :userId");
        query.setParameter("userId", userId);
        return (List<Reservation>) query.list();
    }
}
