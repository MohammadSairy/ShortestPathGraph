public class Review {
    private String id;
    private String text;
    private int rating;

    public Review(String id, String text, int rating) {
        this.id = id;
        this.text = text;
        this.rating = rating;
    }

    // Getter and setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getter and setter for rating
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Override toString method for a readable representation of the object
    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
