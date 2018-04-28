package projeszk.eltecinema.dao;

import projeszk.eltecinema.model.ModelInterface;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class GenericDaoImpl<Entity extends ModelInterface> extends HibernateDaoSupport implements DaoInterface<Entity> {

    protected final Class<Entity> entityClass;

    public GenericDaoImpl(Class<Entity> entityClass, SessionFactory sessionFactor) {
        super.setSessionFactory(sessionFactor);
        this.entityClass = entityClass;
    }

    @Override
    public void updateEntity(Entity entity) {
        currentSession().merge(entity);
        currentSession().flush();
    }

    @Override
    public void deleteEntity(Entity entity) {
        currentSession().delete(entity);
        currentSession().flush();
    }

    @Override
    public void deleteEntityById(Integer id) {
        currentSession().delete(currentSession().load(entityClass, id));
        currentSession().flush();
    }

    @Override
    public boolean insertEntity(Entity entity) {

        try {
            currentSession().persist(entity);
            currentSession().flush();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Entity findEntity(Integer id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.idEq(id));
        Criteria executableCriteria = criteria.getExecutableCriteria(currentSession());
        return (Entity)executableCriteria.uniqueResult();
    }

    @Override
    public boolean exists(Entity entity) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        criteria.add(Restrictions.idEq(entity.getId()));
        criteria.setProjection(Projections.rowCount());
        Criteria executableCriteria = criteria.getExecutableCriteria(currentSession());
        return 1L == (Long) executableCriteria.uniqueResult();
    }

    @Override
    public List<Entity> getEntities() {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        Criteria executeableCriteria = criteria.getExecutableCriteria(currentSession());
        return executeableCriteria.list();
    }
}
