package projeszk.eltecinema.service;

import projeszk.eltecinema.dao.NewsDao;
import projeszk.eltecinema.model.News;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@EqualsAndHashCode(callSuper = true)
@Service
@SessionScope
@Data
public class NewsService extends AbstractService<News> {

    @Autowired
    private NewsDao newsDao;
}
