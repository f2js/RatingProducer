package dk.f2js.ratingproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    private ProducerService service;

    @PostMapping(value = "/rate/{rating}")
    public String sendMyMessageToKafka(@PathVariable("rating") String rating)
    {
        service.sendRating(rating);
        return "Message published: " + rating;
    }
}
