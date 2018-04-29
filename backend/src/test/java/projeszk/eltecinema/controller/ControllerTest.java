package projeszk.eltecinema.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import projeszk.eltecinema.config.ApplicationConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ActorController.class)
@ContextConfiguration(classes={ApplicationConfig.class})
public class ControllerTest {

    private ActorController actorController;
    private CinemaRoomController cinemaRoomController;
    private MovieController movieController;
    private NewsController newsController;
    private ReservationController reservationController;
    private ScreeningController screeningController;
    private UserController userController;

    @Autowired
    private MockMvc actorMockMvc;

    @Autowired
    private MockMvc cinemaRoomMockMvc;
    @Autowired

    private MockMvc movieMockMvc;
    @Autowired

    private MockMvc newsMockMvc;

    @Autowired
    private MockMvc reservationMockMvc;

    @Autowired
    private MockMvc screeningMockMvc;

    @Autowired
    private MockMvc userMockMvc;

    public ControllerTest(ActorController actorController, CinemaRoomController cinemaRoomController, MovieController movieController, NewsController newsController, ReservationController reservationController, ScreeningController screeningController, UserController userController) {
        this.actorController = actorController;
        this.cinemaRoomController = cinemaRoomController;
        this.movieController = movieController;
        this.newsController = newsController;
        this.reservationController = reservationController;
        this.screeningController = screeningController;
        this.userController = userController;

        this.actorMockMvc = MockMvcBuilders.standaloneSetup(actorController).build();
        this.cinemaRoomMockMvc = MockMvcBuilders.standaloneSetup(cinemaRoomController).build();
        this.userMockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        this.screeningMockMvc = MockMvcBuilders.standaloneSetup(screeningController).build();
        this.reservationMockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        this.newsMockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
        this.movieMockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void controllerTests() throws Exception {
        actorControllerTest();
        movieControllerTest();
        screeningControllerTest();
        reservationControllerTest();
        userControllerTest();
    }

    @Test
    public void actorControllerTest() throws Exception {

        MvcResult getAllResult = actorMockMvc.perform(get("/api/actors/getall"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getResult = actorMockMvc.perform(get("/api/actors/0"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(getAllResult.getResponse().getContentAsString());
        Assert.assertNotNull(getResult.getResponse().getContentAsString());

        try {
            JSONArray jsonArray = new JSONArray(getAllResult.getResponse().getContentAsString());
            JSONObject jsonObject = new JSONObject(getResult.getResponse().getContentAsString());

            Assert.assertEquals(jsonObject.get("name"), "Michael");
            Assert.assertEquals(jsonArray.length(), 2);
        } catch (JSONException ex) {
            assert false;
        }

        actorMockMvc.perform(delete("/api/actors/delete/0"));
        MvcResult emptyGetResult = actorMockMvc.perform(get("/api/actors/0"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals("", emptyGetResult.getResponse().getContentAsString());
    }

    @Test
    public void movieControllerTest() throws Exception {
        MvcResult getAllResult = movieMockMvc.perform(get("/api/movies/getall"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getResult = movieMockMvc.perform(get("/api/movies/0"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(getAllResult.getResponse().getContentAsString());
        Assert.assertNotNull(getResult.getResponse().getContentAsString());

        try {
            JSONArray jsonArray = new JSONArray(getAllResult.getResponse().getContentAsString());
            JSONObject jsonObject = new JSONObject(getResult.getResponse().getContentAsString());

            Assert.assertEquals(jsonObject.get("title"), "PULP FICTION");
            Assert.assertEquals(jsonArray.length(), 4);
        } catch (JSONException ex) {
            assert false;
        }

        actorMockMvc.perform(delete("/api/movies/delete/0"));
        MvcResult emptyGetResult = actorMockMvc.perform(get("/api/movies/0"))
                .andReturn();

        Assert.assertEquals("", emptyGetResult.getResponse().getContentAsString());
    }

    @Test
    public void screeningControllerTest() throws Exception {
        MvcResult getAllResult = screeningMockMvc.perform(get("/api/screenings/getall"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getResult = screeningMockMvc.perform(get("/api/screenings/0"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getAllByMovie = screeningMockMvc.perform(get("/api/screenings/getAllByMovie/0"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(getAllResult.getResponse().getContentAsString());
        Assert.assertNotNull(getResult.getResponse().getContentAsString());

        try {
            JSONArray jsonArray = new JSONArray(getAllResult.getResponse().getContentAsString());
            JSONObject jsonObject = new JSONObject(getResult.getResponse().getContentAsString());
            JSONArray byMovieArray = new JSONArray(getAllByMovie.getResponse().getContentAsString());

            Assert.assertEquals(jsonObject.get("startTime"), 1509634800000L);
            Assert.assertEquals(jsonArray.length(), 2);
            Assert.assertEquals(byMovieArray.getJSONObject(0).get("startTime"), 1509634800000L);


        } catch (JSONException ex) {
            assert false;
        }

    }

    @Test
    public void reservationControllerTest() throws Exception {
        MvcResult getAllResult = reservationMockMvc.perform(get("/api/reservations/getall"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getResult = reservationMockMvc.perform(get("/api/reservations/0"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getAllToUser = reservationMockMvc.perform(get("/api/reservations/getAllToUser/0"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getAllToScreening = reservationMockMvc.perform(get("/api/reservations/getAllToScreening/0"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(getAllResult.getResponse().getContentAsString());
        Assert.assertNotNull(getResult.getResponse().getContentAsString());
        Assert.assertNotNull(getAllToUser.getResponse().getContentAsString());
        Assert.assertNotNull(getAllToScreening.getResponse().getContentAsString());

        try {
            JSONArray jsonArray = new JSONArray(getAllResult.getResponse().getContentAsString());
            JSONArray userJsonArray = new JSONArray(getAllToUser.getResponse().getContentAsString());
            JSONArray screeningJsonArray = new JSONArray(getAllToScreening.getResponse().getContentAsString());
            JSONObject jsonObject = new JSONObject(getResult.getResponse().getContentAsString());

            Assert.assertEquals(jsonObject.get("row"), 5);
            Assert.assertEquals(jsonArray.length(), 2);
            Assert.assertEquals(userJsonArray.getJSONObject(0).get("row"), 5);
            Assert.assertEquals(userJsonArray.length(), 2);
            Assert.assertEquals(screeningJsonArray.getJSONObject(0).get("row"), 5);
            Assert.assertEquals(screeningJsonArray.length(), 1);

        } catch (JSONException ex) {
            assert false;
        }
    }

    @Test
    public void userControllerTest() throws Exception {
        MvcResult getAllResult = userMockMvc.perform(get("/user/getall"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult getResult = userMockMvc.perform(get("/user/0"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(getAllResult.getResponse().getContentAsString());
        Assert.assertNotNull(getResult.getResponse().getContentAsString());

        try {
            JSONArray jsonArray = new JSONArray(getAllResult.getResponse().getContentAsString());
            JSONObject jsonObject = new JSONObject(getResult.getResponse().getContentAsString());

            Assert.assertEquals(jsonObject.get("username"), "admin");
            Assert.assertEquals(jsonArray.length(), 2);


            Assert.assertNotNull(userMockMvc.perform(post("/user/login", jsonObject)));

        } catch (JSONException ex) {
            assert false;
        }


    }
}
