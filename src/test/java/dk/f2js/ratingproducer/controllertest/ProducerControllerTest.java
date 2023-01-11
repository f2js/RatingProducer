package dk.f2js.ratingproducer.controllertest;


import com.fasterxml.jackson.databind.ObjectMapper;
import dk.f2js.ratingproducer.RatingProducerApplication;
import dk.f2js.ratingproducer.dto.Rating;
import jakarta.servlet.ServletException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RatingProducerApplication.class)
@AutoConfigureMockMvc
public class ProducerControllerTest {

    @Autowired
    private MockMvc mock;


    @Test
    public void testPostRateValidInput() throws Exception {
        Rating rating = new Rating("4ecc05e55dd98a436ddcc47c", 8);
        mock.perform(post("/rate")
                .contentType("application/json")
                .content(asJsonString(rating)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testPostRateInvalidRatingTooHigh() throws Exception {
        Rating rating = new Rating("4ecc05e55dd98a436ddcc47c", 11);
        assertThrows(ServletException.class, () -> mock.perform(post("/rate")
                .contentType("application/json")
                .content(asJsonString(rating)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)));

    }

    @Test
    public void testPostRateInvalidRatingTooLow() throws Exception {
        Rating rating = new Rating("4ecc05e55dd98a436ddcc47c", 0);
        assertThrows(ServletException.class, () -> mock.perform(post("/rate")
                .contentType("application/json")
                .content(asJsonString(rating)))
                .andExpect(status().is(500))
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)));

    }

    @Test
    public void testPostRateInvalidUserId() throws Exception {
        Rating rating = new Rating("4ecc05e55dd98a436ddcc47cERROR", 8);
        assertThrows(ServletException.class, () -> mock.perform(post("/rate")
                .contentType("application/json")
                .content(asJsonString(rating)))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException)));

    }

    @Test
    public void testPostWrongApplicationType() throws Exception {
        mock.perform(post("/rate")
                .contentType("plain/text"))
                .andExpect(status().is(415));

    }

    @Test
    public void testNotFound() throws Exception {
        mock.perform(get("/rating")
                .contentType("application/json"))
                .andExpect(status().is(404));

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

