package dk.f2js.ratingproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static final String TOPIC = "ratings";
    private static Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendRating(String rating) {
        template.send(TOPIC, rating);
        logger.info("### Producer sends rating [{}]", rating);
        template.flush();
    }



}
