package projeszk.eltecinema.dao;

import projeszk.eltecinema.model.News;
import org.hibernate.SessionFactory;

public class NewsDao extends GenericDaoImpl<News> {

    public NewsDao(Class<News> userClass, SessionFactory sessionFactor) {
        super(userClass, sessionFactor);
    }
}
