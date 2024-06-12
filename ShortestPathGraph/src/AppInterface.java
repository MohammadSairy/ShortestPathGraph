
import java.io.File;
import java.io.IOException;
import java.util.Scanner;




public class AppInterface {






    public static void main(String[] args) throws IOException, InterruptedException {


        Graph G=new Graph("C:\\Users\\amssx\\IdeaProjects\\untitled11\\input.txt");

        while(true){
        try{
            Scanner input=new Scanner(System.in);


            while(true){
                System.out.println("Available choices:");
                System.out.println("            (1) Display all categories");
                System.out.println("            (2) Search the graph for places based on their categories");
                System.out.println("            (3) Display the reviews of a place");
                System.out.println("            (4) calculate the path between two nodes");
                System.out.println("Enter choice number:");
                int choice = input.nextInt();
            switch (choice) {

                case 1:
                    G.printCats();
                    break;
                case 2:
                    System.out.println("enter catagory id: ");
                    choice = input.nextInt();
                    G.printPlacess(Integer.toString(choice));
                    break;
                case 3:
                    System.out.println("enter place id: ");
                    choice = input.nextInt();
                    System.out.println("Do you want newest reviews first? (y/n)");
                    String yn=input.next();
                    if (yn.equalsIgnoreCase("y"))
                        G.printReviews(choice,true);
                    else if (yn.equalsIgnoreCase("n"))
                        G.printReviews(choice,false);
                    else
                        System.out.println("Error, please type y or no only");
                    break;
                case 4:
                    System.out.println("Enter starting node: ");
                    String n1=input.next();
                    System.out.println("Enter Destination node id: ");
                    String n2=input.next();
                    G.calcTrip(n1,n2);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }}}
        catch(Exception e){
            System.out.println("something went wrong, check your input and try again");
            Thread.sleep(3000);

        }



    }}
    public void findPlaces() {
        // Implement the logic to find and list places.
        // This might involve accessing a database or a file with place information.
    }

    public void findNodeOfPlace() {
        // Implement the logic to find a specific node that represents a place.
        // This could involve searching through a collection of nodes.
    }

    public void calcTrip() {
        // Implement the logic to calculate a trip.
        // This could involve calling the BFS algorithm from a Graph class to find a path.
    }


}

