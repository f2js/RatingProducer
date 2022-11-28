FROM openjdk:17-jdk-slim
EXPOSE 9000
ADD target/rating-producer-0.0.1-SNAPSHOT.jar rating-producer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","rating-producer-0.0.1-SNAPSHOT.jar"]