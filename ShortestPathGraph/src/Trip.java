import java.util.LinkedHashSet;
import java.util.Iterator;

public class Trip {
    private LinkedHashSet<Node> path;

    public Trip() {
        path = new LinkedHashSet<>();
    }

    public void addNode(Node node) {
        path.add(node);
    }

    public void print() {
        Iterator<Node> iterator = path.iterator();
        while (iterator.hasNext()) {
            Node current = iterator.next();
            System.out.println("<" + current.getId() + ", " + current.getLatitude() + ", " + current.getLongitude()+">");
            iterator.remove(); // This removes the current node from the collection after printing
        }
    }

    // Getters and setters are omitted for brevity
}


