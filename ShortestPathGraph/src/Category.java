import java.util.HashSet;
import java.util.Iterator;

public class Category {
    private String id;
    private String name;
    private static HashSet<Category> allCategories = new HashSet<>();


    public Category(String id, String name) {
        this.id = id;
        this.name = name;
        allCategories.add(this);
    }

    public Category(String id) {
        Iterator temp= allCategories.iterator();
        while(temp.hasNext()){
            Category temp333=((Category)temp.next());
            if (temp333.getId().equalsIgnoreCase(id)){
                this.id=id;
                this.name=temp333.getName();
            }



        }

    }

    public void printAllCatagories(){
        Iterator temp = allCategories.iterator();
        while(temp.hasNext()){
            System.out.println(temp.next().toString());
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean alreadyexist(String id){
        Iterator temp= allCategories.iterator();
        while(temp.hasNext()){
            if (((Category)temp.next()).getId()==id){
                return true;
            }

        }
        return false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
