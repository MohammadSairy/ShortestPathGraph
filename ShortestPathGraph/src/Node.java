import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Node {
    private String id;
    private double latitude;
    private double longitude;
    private LinkedHashSet<Place> places;

    public Node(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.places = new LinkedHashSet<>();
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public void printPlacesByCategory(String category) {
        for (Place place : places) {
            if (place.hasCategory(category)) {
                System.out.println(this.id+" has "+place.getName()+" (placeID="+place.getId()+")");
            }
        }
    }

    public void printReviewsByPlaceId(String placeId,boolean b) {
        for (Place place : places) {
            if (place.getId().equals(placeId)) {
                place.printReviews(b);
                break;
            }
        }
    }

    public static double calcDistance(Node start, Node end) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(end.latitude - start.latitude);
        double lonDistance = Math.toRadians(end.longitude - start.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(start.latitude)) *
                Math.cos(Math.toRadians(end.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Double.compare(node.latitude, latitude) == 0 &&
                Double.compare(node.longitude, longitude) == 0 &&
                Objects.equals(id, node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }

    // Getters and setters omitted for brevity

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Place getPlace(String id) {
        Iterator temp=places.iterator();
        while(temp.hasNext()){
            Place tempoo=((Place) temp.next());
            if(tempoo.getId().equalsIgnoreCase(id)){
                return tempoo;
            }

        }
        return null;
    }

    public void printCats(){
        Iterator temp=places.iterator();
        Place temp2=(Place)temp.next();
        temp2.printCats();

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPlaces(LinkedHashSet<Place> places) {
        this.places = places;
    }

    public boolean existplace(){
            Iterator tempo=this.places.iterator();
            while(tempo.hasNext()){
                Place temp=(Place)tempo.next();
                if (temp!=null);
                return true;
            }
        return false;
    }

    public void printPlacesbasedon(){


    }
}
