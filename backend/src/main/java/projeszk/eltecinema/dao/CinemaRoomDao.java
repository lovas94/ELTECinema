package projeszk.eltecinema.dao;

import projeszk.eltecinema.model.CinemaRoom;
import org.hibernate.SessionFactory;

public class CinemaRoomDao extends GenericDaoImpl<CinemaRoom> {

    public CinemaRoomDao(Class<CinemaRoom> cinemaRoomClass, SessionFactory sessionFactor) {
        super(cinemaRoomClass, sessionFactor);
    }
}
