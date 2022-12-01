package dk.f2js.ratingproducer.dto;

public class Rating {
    private String userId;
    private Integer rating;

    public Rating(String userId, Integer rating) {
        this.userId = userId;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }


    public Integer getRating() {
        return rating;
    }


    @Override
    public String toString() {
        return "Rating{" +
                "userId='" + userId + '\'' +
                ", rating=" + rating +
                '}';
    }
}
