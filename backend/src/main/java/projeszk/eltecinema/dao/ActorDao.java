package projeszk.eltecinema.dao;

import projeszk.eltecinema.model.Actor;
import org.hibernate.SessionFactory;

public class ActorDao extends GenericDaoImpl<Actor> {
    public ActorDao(Class<Actor> actorClass, SessionFactory sessionFactor) {
        super(actorClass, sessionFactor);
    }
}
