# RatingProducer
### Group members  

[@josefmarcc ](https://github.com/josefmarcc)
[@fdinsen](https://github.com/fdinsen)
[@sebastianbentley ](https://github.com/SebastianBentley)
[@dahlfrederik ](https://github.com/dahlfrederik)

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/f2js/RatingProducer/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/f2js/RatingProducer/tree/main)

A producer written in Java, for producing rating event to Kafka.

### POST /kakfa/rate/{rating}
Gets all orders for a given customer. Does not fetch orderlines.

#####body:
 - rating: "Integer between 1 to 10"
 - userId: Id of user


 #### Response:
 - 200 OK: Rating published: {rating}
 - 400 Bad Request: The request body was missing or invalid.
 - 500 Internal Server Error: An error occurred on the server side.

This message is produced by the [ProducerService.java](https://github.com/f2js/RatingProducer/blob/main/src/main/java/dk/f2js/ratingproducer/producer/ProducerService.java)
