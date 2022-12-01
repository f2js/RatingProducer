package dk.f2js.ratingproducer.acceptance;

import dk.f2js.ratingproducer.dto.Rating;
import dk.f2js.ratingproducer.service.ProducerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class StepDefinitions {

    @Mock
    ProducerService serviceMock = mock(ProducerService.class);
    Rating rating;

    @Given("^a rating of (\\d+) out of (\\d+) is rated by a user$")
    public void aRatingOfOutOfIsRatedByAUser(int arg0, int arg1) {
        rating = new Rating("4ecc05e55dd98a436ddcc47c", 8);
    }

    @When("^a post request is sent with rating in the body$")
    public void aPostRequestIsSentWithRatingInTheBody() {
        doNothing().when(serviceMock).sendRating(rating);
        serviceMock.sendRating(rating);
    }

    @Then("^a kafka event is produced$")
    public void aKafkaEventIsProduced() {
        verify(serviceMock, times(1)).sendRating(rating);
    }


}
