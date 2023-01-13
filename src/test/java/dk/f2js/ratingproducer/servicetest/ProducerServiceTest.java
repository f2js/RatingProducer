package dk.f2js.ratingproducer.servicetest;

import dk.f2js.ratingproducer.dto.Rating;
import dk.f2js.ratingproducer.service.ProducerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProducerServiceTest {

	@Mock
	ProducerService serviceMock = new ProducerService();
	ProducerService service = new ProducerService();

	@Mock
	KafkaTemplate<String, String> template = mock(KafkaTemplate.class);

	@Test
	void contextLoads() {
	}

	@Test
	public void testVerifyMock() {
		Rating rating = new Rating("4ecc05e55dd98a436ddcc47c", 8);
		doNothing().when(serviceMock).sendRating(rating, template);
		serviceMock.sendRating(rating, template);
		verify(serviceMock, times(1)).sendRating(rating, template);
	}

	@Test
	public void testValidIdAndRating() {
		Rating rating = new Rating("4ecc05e55dd98a436ddcc47c", 9);
		serviceMock.sendRating(rating, template);
		verify(serviceMock, times(1)).sendRating(rating, template);
	}

	@Test
	public void testRatingTooHigh() {
		Rating rating = new Rating("4ecc05e55dd98a436ddcc47c", 11);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			service.sendRating(rating, template);
		});
		String expectedMessage = "Invalid rating";
		String actualMessage = exception.getMessage();
		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testRatingTooLow() {
		Rating rating = new Rating("4ecc05e55dd98a436ddcc47c", 0);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			service.sendRating(rating, template);
		});
		String expectedMessage = "Invalid rating";
		String actualMessage = exception.getMessage();
		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testRatingInvalidId() {
		Rating rating = new Rating("4ecc05e55dd98a436ddcc47cERROR", 8);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			service.sendRating(rating, template);
		});
		String expectedMessage = "Invalid rating";
		String actualMessage = exception.getMessage();
		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testRatingInvalidSymbols() {
		Rating rating = new Rating("4ecc05e55dd98a436ddcc4<>", 8);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			service.sendRating(rating, template);
		});
		String expectedMessage = "Invalid rating";
		String actualMessage = exception.getMessage();
		Assertions.assertTrue(actualMessage.contains(expectedMessage));
	}
}
