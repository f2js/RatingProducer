package dk.f2js.ratingproducer.controller;

import dk.f2js.ratingproducer.dto.Rating;
import dk.f2js.ratingproducer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class ProducerController {

    @Autowired
    private ProducerService service;

    @Autowired
    private KafkaTemplate<String, String> template;

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public String produceRating(@RequestBody Rating rating)
    {
        service.sendRating(rating, template);
        return "Rating published: " + rating;
    }
}
