import java.util.Objects;

public class Customer implements Comparable<Customer>, Identifiable {
    private static int nextID = 0;
    private final String NAME;
    private final Integer ID;
    private final VideoCollection<Video> rentedVideos;

    public Customer(String name, VideoCollection<Video> rentedVideos){
        this.NAME = name;
        this.ID = nextID++;
        this.rentedVideos = rentedVideos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer c = (Customer) o;
        return Objects.equals(NAME, c.NAME) && Objects.equals(ID, c.ID);
    }
    public int hashCode() {
        return Objects.hash(NAME, ID);
    }
    public String getName(){return NAME;}
    public Integer getID(){return ID;}
    public VideoCollection<Video> getRentVideos(){return rentedVideos;}
    public String toString(){
        return "[" + getID() + "] " + getName();
    }

    @Override
    public int compareTo(Customer o) {
        return ID.compareTo(o.ID);
    }
}
