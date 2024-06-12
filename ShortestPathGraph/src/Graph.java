import java.io.*;
import java.util.*;

public class Graph {
    private Map<String, Node> nodes;
    private Map<Node, SortedSet<Node>> adjacentNodes;

    public Graph(String filePath) throws IOException {
        // This constructor would parse the file content to construct nodes and adjacency lists.
        // For this example, we'll assume the file parsing is done elsewhere and these maps are filled accordingly.
        nodes = new HashMap<>();
        adjacentNodes = new HashMap<>();
        File f=new File(filePath);
        FileReader f2=new FileReader(f);
        BufferedReader reader = new BufferedReader(f2);
        String line;
        String command="0";
        while((line = reader.readLine()) != null){
            if (line.startsWith("//")){
                command=line;
                continue;
            }
            if (command.equalsIgnoreCase("//Category Definitions")){
                String parts[]=line.split(",");
                Category temp=new Category(parts[0],parts[1]);
            }
            if (command.equalsIgnoreCase("//Node Definitions")){
                String parts[]=line.split(",");
                Node temp=new Node(parts[0],Double.parseDouble(parts[1]),Double.parseDouble(parts[2]));
                this.nodes.put(parts[0],temp);
            }
            if (command.equalsIgnoreCase("//Adjacent Nodes")){
                String parts[]=line.split("->");
                this.addAdjacentNode(this.nodes.get(parts[0]),this.nodes.get(parts[1]));

            }
            if (command.equalsIgnoreCase("//Places at nodes")){
                String parts[]=line.split("->");
                String part2[]=parts[1].split(",");

                Place temp=new Place(part2[0],part2[1]);
                String parts3[]=part2[2].split(";");
                for (int i=0; i<parts3.length; i++){
                    if (parts3[i]!=null){
                        temp.addCategory(parts3[i]);
                    }
                }
                this.getNode(parts[0]).addPlace(temp);

            }
            if (command.equalsIgnoreCase("//Place Reviews")){
                String parts[]=line.split("->");
                String parts2[]=parts[1].split(",");
                Iterator temp= this.nodes.values().iterator();
                while(temp.hasNext()){
                    Node temp3=((Node)temp.next());
                    if (temp3.getPlace(parts[0])!=null){
                        Review temp2=new Review(parts2[0],parts2[1],Integer.parseInt(parts2[2]));
                        temp3.getPlace(parts[0]).addReview(temp2);
                    }
                }
            }

        }

    }

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    /*public void addAdjacentNode(Node node, Node adjacent) {
        adjacentNodes.computeIfAbsent(node, k -> new TreeSet<>(Comparator.comparingDouble(n -> node.calcDistance( node, adjacent))));
        adjacentNodes.get(node).add(adjacent);
    }*/
    public void addAdjacentNode(Node baseNode, Node adjacentNode) {

        adjacentNodes.computeIfAbsent(baseNode, k -> new TreeSet<>(
                Comparator.comparingDouble(node -> baseNode.calcDistance(baseNode, node))
        ));
        adjacentNodes.get(baseNode).add(adjacentNode);
    }

    public Trip calculateTrip(Node start, Node end) {
        Set<String> visited = new HashSet<>();
        List<Node> path = new ArrayList<>();

        // Start the DFS from the start node
        boolean found = dfs(start, end, visited, path);

        Trip trip = new Trip();
        if (found) {
            for (Node node : path) {
                trip.addNode(node);
            }
        }

        return trip;
    }

    // Helper method for DFS
    private boolean dfs(Node current, Node end, Set<String> visited, List<Node> path) {
        // If the node is already visited, we skip it
        if (!visited.add(current.getId())) {
            return false;
        }

        // Add current node to path
        path.add(current);

        // If we've reached the end node, we're done
        if (current.equals(end)) {
            return true;
        }

        // Visit all adjacent nodes
        SortedSet<Node> adjNodes = this.adjacentNodes.getOrDefault(current, new TreeSet<>());
        for (Node adj : adjNodes) {
            if (dfs(adj, end, visited, path)) {
                return true;
            }
        }

        // If we're here, it means current path doesn't lead to end
        // Remove current node from path and backtrack
        path.remove(path.size() - 1);
        return false;
    }

/*    public void printPlaces(String category) {
        for (Node node : nodes.values()) {
            for (Place place : node.getPlaces()) {
                if (place.hasCategory(category)) {
                    System.out.println(place);
                }
            }
        }
    }*/

/*    public void printReviews(String placeId, boolean backward) {
        Node node = nodes.get(placeId);
        if (node != null) {
            for (Place place : node.getPlaces()) {
                place.printReviews(backward);
            }
        }
    }*/

    public Node getNode(String id) {
        return this.nodes.get(id);
    }
// Getters and setters omitted for brevity

    public void printCats(){
        Iterator temp=nodes.values().iterator();

        while(temp.hasNext()){
            Node tempo=((Node)temp.next());
            if (tempo.existplace()){
                tempo.printCats();
                break;
            }

        }


    }
    public void printPlacess(String id){
        String temp=Integer.toString(id);
        Iterator ite=this.nodes.values().iterator();
        while(ite.hasNext()){
            Node mynode=((Node)ite.next());
            mynode.printPlacesByCategory(temp);
        }

    }

    public void printReviews(String id,boolean b){

        Iterator ite=this.nodes.values().iterator();
        while(ite.hasNext()){
            Node mynode=((Node)ite.next());
            mynode.printReviewsByPlaceId(id,b);
        }
    }

    public void calcTrip(String id1,String id2){
        Node node1=null;
        Node node2=null;
        Iterator temp=this.nodes.values().iterator();
        while(temp.hasNext()){
            Node mynode=((Node)temp.next());
            if (mynode.getId().equalsIgnoreCase(id1)){
                node1=mynode;
                break;
            }


        }
        Iterator temp2=this.nodes.values().iterator();
        while(temp2.hasNext()){
            Node mynode=((Node)temp2.next());
            if (mynode.getId().equalsIgnoreCase(id2)){
                node2=mynode;
                break;
            }

        }
        if (node1==null || node2==null){
            System.out.println("Couldn't find the nodes you were looking for");
        }
        else {
            this.calculateTrip(node1,node2).print();
        }

    }

}


