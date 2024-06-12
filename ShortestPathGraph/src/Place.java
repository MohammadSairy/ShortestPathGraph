import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class Place {
    private String id;
    private String name;
    private Set<Category> categories;
    private LinkedHashSet<Review> reviews;

    public Place(String id, String name) {
        this.id = id;
        this.name = name;
        this.categories = new HashSet<>();
        this.reviews = new LinkedHashSet<>();
    }

    public void addCategory(String category) {
        Category temp=new Category(category);
        categories.add(temp);
    }

    public boolean hasCategory(String category) {
        Iterator temp=this.categories.iterator();
        while(temp.hasNext()){
            Category tempc=(Category)temp.next();
            if (tempc.getId().equalsIgnoreCase(category)){
                return true;
            }

        }
        return false;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void printReviews(boolean backward) {
        if (backward) {
            Iterator<Review> iterator = reviews.iterator();
            // Convert to array to access in reverse order
            Review[] reviewsArray = reviews.toArray(new Review[0]);
            for (int i = reviewsArray.length - 1; i >= 0; i--) {
                System.out.println(reviewsArray[i]);
            }
        } else {
            for (Review review : reviews) {
                System.out.println(review);
            }
        }
    }

    // Getters and setters for id and name
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void printCats(){
        Iterator temp=categories.iterator();

            Category tempo=(Category)temp.next();
            tempo.printAllCatagories();



    }



    // Assume the Review class is already defined
}