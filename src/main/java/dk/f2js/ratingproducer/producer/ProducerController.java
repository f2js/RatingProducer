package dk.f2js.ratingproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/hello")
    public String hello()
    {
        return "Hello from producer";
    }
}
