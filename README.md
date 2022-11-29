# RatingProducer
### Group members  

[@josefmarcc ](https://github.com/josefmarcc)
[@fdinsen](https://github.com/fdinsen)
[@sebastianbentley ](https://github.com/SebastianBentley)
[@dahlfrederik ](https://github.com/dahlfrederik)

We have developed some simple startcode for a producer and consumer with Kafka and Java 17. In this example, our Kafka is running in a Kubernetes Cluster.


# How to run the project 

### Setup 
Before running this project, setup Kafka. We recommend using docker, and if you have a kubernetes cluster, we used this guide to set it up:
[Kafka with K8s](https://levelup.gitconnected.com/how-to-deploy-apache-kafka-with-kubernetes-9bd5caf7694f). After kafka is setup, replace ${CLUSTER_IP} in the [application.yml](https://github.com/f2js/RatingProducer/blob/main/src/main/resources/application.yml), with the IP for the kafka broker (or the kafka-broker-service in K8s).



#### Try it out
There are different ways of running the program:
1. Using an IDE.
2. Build and run the Dockerfile.
3. Take it all the way to Kubernetes!

When you have run the program, you can send a message with "IP"/kakfa/rate/{rating}
This message is produced by the [ProducerService](https://github.com/f2js/RatingProducer/blob/main/src/main/java/dk/f2js/ratingproducer/producer/ProducerService.java), and consumed with the [ConsumerService.java](https://github.com/f2js/RatingProducer/blob/main/src/main/java/dk/f2js/ratingproducer/consumer/ConsumerService.java). 

Now, kafka can handle a data stream of ratings, and consume them properly!
