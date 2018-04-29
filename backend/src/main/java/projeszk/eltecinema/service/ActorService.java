package projeszk.eltecinema.service;

import projeszk.eltecinema.model.Actor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@EqualsAndHashCode(callSuper = true)
@Service
@SessionScope
@Data
public class ActorService extends AbstractService<Actor> {
}
