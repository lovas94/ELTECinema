package projeszk.eltecinema.service;

import projeszk.eltecinema.dao.ReservationDao;
import projeszk.eltecinema.model.Reservation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Service
@SessionScope
@Data
public class ReservationService extends AbstractService<Reservation> {

    @Autowired
    ReservationDao reservationDao;

    public List<Reservation> getAllReservationsToUser(Integer userId) {
        return reservationDao.getAllReservationToUser(userId);
    }

    public List<Reservation> getAllReservationsToScreening(Integer screeningId) {
        return reservationDao.getAllReservationsToScreening(screeningId);
    }
}
