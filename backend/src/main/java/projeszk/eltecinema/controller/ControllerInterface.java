package projeszk.eltecinema.controller;


import projeszk.eltecinema.model.ModelInterface;

import java.util.List;

public interface ControllerInterface<Entity extends ModelInterface> {

    void update(Entity entity);
    void delete(Entity entity);
    void create(Entity entity);
    void deleteById(Integer id);
    Entity get(Integer id);
    List<Entity> getAll();
}
