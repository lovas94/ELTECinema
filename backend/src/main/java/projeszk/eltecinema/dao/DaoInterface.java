package projeszk.eltecinema.dao;

import java.util.List;

public interface DaoInterface<Entity> {

    void updateEntity(Entity entity);
    void deleteEntity(Entity entity);
    void deleteEntityById(Integer id);
    boolean insertEntity(Entity entity);
    Entity findEntity(Integer id);
    boolean exists(Entity entity);
    List<Entity> getEntities();
}
