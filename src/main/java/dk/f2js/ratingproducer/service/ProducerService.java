package dk.f2js.ratingproducer.service;

import com.google.gson.Gson;
import dk.f2js.ratingproducer.dto.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProducerService {

    private static final String TOPIC = "ratings";
    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
    private static final Gson gson = new Gson();
    private final Pattern pattern = Pattern.compile("^[a-fA-F0-9]{24}$");

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendRating(Rating rating) {
        Matcher matcher = pattern.matcher(rating.getUserId());
        if (rating.getRating() >= 1 && rating.getRating() <= 10 && matcher.find()) {
            String result = gson.toJson(rating);
            template.send(TOPIC, result);
            logger.info("### Producer sends rating [{}]", rating);
            template.flush();
        } else {
            logger.warn("Invalid rating, with rating [{}]", rating);
            throw new RuntimeException("Invalid rating");
        }
    }


}
