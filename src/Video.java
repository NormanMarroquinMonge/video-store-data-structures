import java.util.Objects;

public class Video implements Comparable<Video>, Identifiable {
    private static int nextID = 0;
    private final String TITLE;
    private final Integer ID;

    public Video(String title){
        this.ID = nextID++;
        this.TITLE = title;
    }

    public Video(String title, int ID){
        this.ID = ID;
        this.TITLE = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(TITLE, video.TITLE) && Objects.equals(ID, video.ID);

    }
    public String getName(){return TITLE;}
    public Integer getID(){return ID;}
    public String toString(){return "[" + getID() + "] " + getName();}

    @Override
    public int compareTo(Video o) {
        return ID.compareTo(o.ID);
    }
}
