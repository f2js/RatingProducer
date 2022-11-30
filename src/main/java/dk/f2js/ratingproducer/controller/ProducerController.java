package dk.f2js.ratingproducer.controller;

import dk.f2js.ratingproducer.dto.Rating;
import dk.f2js.ratingproducer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class ProducerController {

    @Autowired
    private ProducerService service;

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public String produceRating(@RequestBody Rating rating)
    {
        service.sendRating(rating);
        return "Rating published: " + rating;
    }
}
