package projeszk.eltecinema.controller;

import projeszk.eltecinema.annotation.Role;
import projeszk.eltecinema.model.News;
import projeszk.eltecinema.model.User;
import projeszk.eltecinema.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController implements ControllerInterface<News> {

    @Autowired
    NewsService newsService;

    @Override
    public void update(News news) {

    }

    @Override
    public void delete(News news) {

    }

    @Override
    public void create(News news) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/{id}")
    public News get(Integer id) {
        return newsService.get(id);
    }

    @Role({User.Role.ADMIN, User.Role.USER})
    @GetMapping("/getAll")
    @Override
    public List<News> getAll() {
        return newsService.getAll();
    }
}
